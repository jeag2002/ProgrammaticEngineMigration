%%%-------------------------------------------------------------------
%%% @author jeag2
%%% @copyright (C) 2019, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 02. ene 2019 20:24
%%%-------------------------------------------------------------------
-module(rest).
-author("jeag2").

-include("C:/Program Files (x86)/erl6.3/lib/stdlib-2.3/include/qlc.hrl").
-include("C:/Program Files (x86)/erl6.3/include/yaws_api.hrl").

%% API
-export([out/1]).

box(Str) ->
  {'div',[{class,"box"}],
    {pre,[],Str}}.

method(Arg) ->
  Rec = Arg#arg.req,
  Rec#http_request.method.

keenkale()->

  Method = post,
  URL = "http://rtb-useast.keenkale.com/rtb?zone=55764",
  Header = [
    {"Content-Type","application/json"},
    {"Connection","Keep-Alive"},
    {"Accept-Charset","UTF-8"},
    {"Expect","x-openrtb-version: 2.4"},
    {"x-forwarded-for","216.15.125.142"},
    {"x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36"},
    {"x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36"}
  ],
  Type = "application/json",
  Body = "{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}",
  %Body = "{\"name\":\"foo.example.com\"}",
  HTTPOptions = [],
  Options = [],
  inets:start(),
  R = httpc:request(Method, {URL, Header, Type, Body}, HTTPOptions, Options),
  ResponseData = lists:flatten(io_lib:format("~p", [R])),
  Response = io_lib:format("Keenkale ~p",[ResponseData]),
  Response.

parse(Arg) ->
  Index = string:sub_string(Arg#arg.appmoddata,19),
  Result = if Index =:= "271" -> keenkale();
              true -> io_lib:format("integration not found ~p~n",[Index])
           end,
  Result.


handle('GET', _Arg) ->
  {ehtml, [{p,[], box(parse(_Arg))}]}.


out(Arg) ->
  Method = method(Arg) ,
  io:format("~p:~p ~p Request ~n", [?MODULE, ?LINE, Method]),
  handle(Method,Arg).





