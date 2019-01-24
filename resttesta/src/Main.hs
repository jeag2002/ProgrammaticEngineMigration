--module Main where

--main :: IO ()
--main = do
--  putStrLn "hello world"

-- https://github.com/sdiehl/haskell-warp-rest
-- https://github.com/scotty-web/scotty/issues/134
-- https://haskell-lang.org/library/http-client
-- https://github.com/crabmusket/haskell-simple-concurrency/blob/master/src/tutorial.md
-- https://github.com/snoyberg/http-client/blob/master/TUTORIAL.md
-- https://haskell-lang.org/library/aeson

{-# LANGUAGE OverloadedStrings, TypeFamilies, DeriveDataTypeable, TemplateHaskell #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}
{-# LANGUAGE DeriveGeneric #-}

import           Control.Exception
import           Data.Default
import           Data.Monoid               (mconcat)
import           Data.Text.Lazy            (Text,pack)
import           Data.Typeable
import           Network.HTTP.Types.Status
import           Network.Wai
import           Network.Wai.Handler.Warp
import           Web.Scotty

import qualified Network.HTTP.Conduit (requestHeaders, requestBody)

import           Network.HTTP.Simple

import           Web.Scotty.Internal.Types
import           Control.Monad.IO.Class

import           Data.Aeson (encode, object, (.=))



data Ex = NotFound | UserEx Text
    deriving (Show, Typeable)
instance Exception Ex

main :: IO ()
main = do
    let opts = setSettings (def)
    scottyOpts opts $ do
      get "/rtbdispatcher/rtb/:word" $ do
        value <- param "word"
        let valueStr = toString value
        case valueStr of
          "" -> throw NotFound
          "271" -> processKenkale valueStr
          _ -> html $ mconcat["Integration id: ",valueStr, " Not found"]
 where
    setSettings opts = let s = settings opts
                       in opts { settings = setPort 8082 (setOnExceptionResponse onE s) }
    onE e = case fromException e of
        Just NotFound -> responseLBS status200 [] "No Integration ID found"
        Just (UserEx _) -> responseLBS status500 [] "Internal server error"
        _ -> responseLBS status500 [] "Something went wrong"


processKenkale :: Text->ActionT Text IO ()

processKenkale s = do

             let requestObject = object
                         [ "name" .= ("Alice" :: String)
                         , "age"  .= (35 :: Int)
                         ]

             requestIni <- parseRequest "POST http://rtb-useast.keenkale.com/rtb?zone=55764"

             let request = setRequestMethod "POST"
                           $ setRequestHeaders [
                           ("Content-Type","application/json"),
                           ("Connection","Keep-Alive"),
                           ("Accept-Charset","UTF-8"),
                           ("Expect","x-openrtb-version: 2.4"),
                           ("x-forwarded-for","216.15.125.142"),
                           ("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36"),
                           ("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.3")
                           ]
                           $ setRequestBodyLBS "{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}"
                           $ requestIni
             -- setRequestBodyJSON requestObject
             response <- httpLBS request
             let responseCodeStatus = " Status code: " ++ show (getResponseStatusCode  response)
             let responseData = responseCodeStatus::String
             let responseText = pack responseData
             let responseStr = mconcat["Integration ",s, " done ", responseText]
             text responseStr


toString :: Text -> Text
toString s = s
