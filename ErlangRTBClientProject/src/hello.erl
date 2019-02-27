%%https://sites.google.com/site/gettingalongwitherlang/home/distributed-programming/light-weight-process/spawning-multiple-processes
%%https://www.tutorialspoint.com/erlang/erlang_concurrency.htm
%%https://pragdave.me/blog/2007/04/16/adding-concurrency-to-our-erlang-program.html
%%https://learnyousomeerlang.com/errors-and-exceptions
%%https://learnyousomeerlang.com/errors-and-processes
%%https://erlangbyexample.org/monitors
%%http://erlangbyexample.org/links

-module(hello).
-export([start/0,clientHTTP/1]).
-include("C:/Program Files (x86)/erl6.3/lib/stdlib-2.3/include/qlc.hrl").

start() ->
  spawn_many(10).

spawn_many(0)-> ok;
spawn_many(N) ->
  MyPID = self(),
  spawn(fun() -> clientHTTP(MyPID) end),
  spawn_many(N-1),
  receive
    { answer, Val } -> { "Child process said", Val }, io:format("processing ~p\n", [Val])
  end.


clientHTTP(Parent) ->
    inets:start(),
    R = httpc:request("http://localhost:8082/rtbdispatcher/rtb/271"),
    ResponseData = lists:flatten(io_lib:format("~p", [R])),
    Response = io_lib:format("~p Client connection ~p",[self(),ResponseData]),
    Parent ! { answer, Response }.


