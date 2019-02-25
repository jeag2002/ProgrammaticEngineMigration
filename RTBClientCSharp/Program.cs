/*
http://www.learncsharptutorial.com/threadpooling-csharp-example.php
*/



using System;
using System.Threading;
using System.Net;

using System.Diagnostics;
using System.IO;

namespace Client
{
    
    class Task
    {
        private ManualResetEvent _doneEvent;
        private int N;

        public Task(int n, ManualResetEvent doneEvent){

            N = n;
            _doneEvent = doneEvent;

        }


        public String processGet(){
             String result = "";

            try{
                HttpWebRequest request = (HttpWebRequest) WebRequest.Create("http://localhost:8082/rtbdispatcher/rtb/271");
                HttpWebResponse response = (HttpWebResponse) request.GetResponse();
                StreamReader stream = new StreamReader(response.GetResponseStream());
                result =  "Status code [" + (int)response.StatusCode + "] Body [" +  stream.ReadToEnd() + "]";
            } catch(Exception ex){
                result = ex.Message;
            }    

            return result;
        }

        public void ThreadPoolCallback(Object threadContext)
        {
            String data = processGet();
            Console.WriteLine("Client execution [" + N + "] " +  data); 
            _doneEvent.Set();

        }


    }
    
    
    class Program
    {

        private ManualResetEvent _doneEvent;

        static void Main(string[] args)
        {
           Stopwatch mywatch = new Stopwatch();

            mywatch.Start();
            ProcessWithThreadPoolMethod();
            mywatch.Stop();

            Console.WriteLine("Time consumed by ProcessWithThreadPoolMethod is : " + mywatch.ElapsedTicks.ToString());
            mywatch.Reset();


        }

        static void ProcessWithThreadPoolMethod()
        {
            var doneEvents = new ManualResetEvent[10];
            
            for (int i = 0; i < 10; i++)
            {
                doneEvents[i] = new ManualResetEvent(false);
                var task = new Task(i, doneEvents[i]);
                ThreadPool.QueueUserWorkItem(task.ThreadPoolCallback,i);
            }

            WaitHandle.WaitAll(doneEvents);
        }

    }
}
