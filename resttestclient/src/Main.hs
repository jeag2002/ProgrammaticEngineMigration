--https://en.wikipedia.org/wiki/Concurrent_Haskell
--https://www.fpcomplete.com/blog/2016/11/comparative-concurrency-with-haskell
--https://medium.com/@jonathangfischoff/quick-and-easy-concurrency-with-haskell-16ef41576a3e


--module Main where
--main :: IO ()
--main = putStrLn "hello world"

{-# LANGUAGE DataKinds     #-}
{-# LANGUAGE DeriveGeneric #-}
{-# LANGUAGE OverloadedStrings #-}
{-# LANGUAGE FlexibleInstances #-}

import           Control.Concurrent (forkIO, threadDelay, writeChan, readChan, newChan)
import           Data.IORef (atomicModifyIORef, newIORef)
import           Control.Monad (replicateM_,forever)
import           Control.Monad.IO.Class (MonadIO (..), MonadIO, liftIO)
import           System.Random (randomRIO)
import           System.IO

import           Network.HTTP.Conduit

import           Data.Text.Lazy            (Text,pack,unpack)

import           Control.Exception
import           Data.ByteString.Lazy.Internal

import qualified Data.ByteString.Char8 as B
import qualified Data.ByteString.Lazy as L

workerCount, workloadCount, minDelay, maxDelay :: Int

workerCount = 10
workloadCount = 10000
minDelay = 250000
maxDelay = 750000


catchAny :: IO a -> (SomeException -> IO a) -> IO a
catchAny = Control.Exception.catch

processHttpClient :: IO String
processHttpClient = do

    initReq <- parseRequest "http://localhost:8082/rtbdispatcher/rtb/271"
    manager <- newManager tlsManagerSettings
    res <- httpLbs initReq manager
    return .show . responseBody $ res

worker requestChan responseChan workerId = forkIO $ forever $ do

     delay <- randomRIO (minDelay, maxDelay)
     threadDelay delay
     int <- readChan requestChan
     str <- catchAny processHttpClient $ \e -> do
            let strerr = "Exception " ++  show e
            return strerr

     let processOut = pack str
     writeChan responseChan (workerId, processOut)

main :: IO ()
main = do

      requestChan <- newChan
      responseChan <- newChan
      mapM_ (worker requestChan responseChan) [1..workerCount]

      let perInteger int = do

             writeChan requestChan int
             (workerId, result) <- readChan responseChan
             putStrLn $ concat [ " Worker ", show workerId, " result " , show result ]

      mapM_ perInteger [1..workerCount]

