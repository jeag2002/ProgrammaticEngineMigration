#include <boost/beast/core.hpp>
#include <boost/beast/http.hpp>
#include <boost/beast/version.hpp>
#include <boost/asio.hpp>

#include <boost/property_tree/ptree.hpp>
#include <boost/property_tree/json_parser.hpp>


#include <chrono>
#include <cstdlib>
#include <ctime>
#include <iostream>
#include <memory>
#include <string>

namespace beast = boost::beast;
namespace http = beast::http;
namespace net = boost::asio;

using tcp = boost::asio::ip::tcp;

using boost::property_tree::ptree;
using boost::property_tree::read_json;
using boost::property_tree::write_json;


namespace my_program_state
{
    std::size_t
    request_count()
    {
        static std::size_t count = 0;
        return ++count;
    }

    std::time_t
    now()
    {
        return std::time(0);
    }

    std::string
    otherIntegration()
    {
        return "Other integration not found\n";
    }
}

class http_connection : public std::enable_shared_from_this<http_connection>
{
public:
    http_connection(tcp::socket socket)
        : socket_(std::move(socket))
    {
    }

    void
    start()
    {
        read_request();
        check_deadline();
    }

private:
    tcp::socket socket_;

    beast::flat_buffer buffer_{8192};

    http::request<http::dynamic_body> request_;

    http::response<http::dynamic_body> response_;

    net::basic_waitable_timer<std::chrono::steady_clock> deadline_{
        socket_.get_executor().context(), std::chrono::seconds(60)};

    void
    read_request()
    {
        auto self = shared_from_this();

        http::async_read(
            socket_,
            buffer_,
            request_,
            [self](beast::error_code ec,
                std::size_t bytes_transferred)
            {
                boost::ignore_unused(bytes_transferred);
                if(!ec)
                    self->process_request();
            });
    }

    void
    process_request()
    {
        response_.version(request_.version());
        response_.keep_alive(false);

        switch(request_.method())
        {
        case http::verb::get:
            response_.result(http::status::ok);
            response_.set(http::field::server, "Beast");
            create_response();
            break;

        default:
            response_.result(http::status::bad_request);
            response_.set(http::field::content_type, "text/plain");
            beast::ostream(response_.body())
                << "Invalid request-method '"
                << std::string(request_.method_string())
                << "'";
            break;
        }

        write_response();
    }

    std::string keenKaleIntegration(){

        try
        {
            std::string json_str("{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}");

            //ptree pt2;
            //std::istringstream is_1 (json_str);
            //read_json(is_1,pt2);
            //std::string json_str_1 = pt2.get<std::string>("id");

            auto const host = "rtb-useast.keenkale.com";
            auto const port = "80";
            auto const target = "/rtb?zone=55764";
            int version = 11;

            net::io_context ioc;
            tcp::resolver resolver{ioc};
            tcp::socket socket{ioc};

            auto const results = resolver.resolve(host,port);

            boost::asio::connect(socket, results.begin(), results.end());

            http::request<http::string_body> req{http::verb::post,target,version};

            req.set("Content-Type","application/json");
            req.set("Connection","Keep-Alive");
            req.set("Accept-Charset","UTF-8");
            req.set("Expect","x-openrtb-version: 2.4");
            req.set("x-forwarded-for","216.15.125.142");
            req.set("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            req.set("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            req.set(http::field::host,"rtb-useast.keenkale.com");
            req.set(http::field::user_agent,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");

            auto const json(json_str);

            req.body() = json;
            req.prepare_payload();

            std::cout << "KEENKALE integration "<< std::endl;

            http::write(socket,req);
            beast::flat_buffer buffer;

            http::response<http::dynamic_body> res;
            http::read(socket,buffer,res);

            std::stringstream buffer_1;
            buffer_1 << res << std::endl;

            boost::system::error_code ec;
            socket.shutdown(tcp::socket::shutdown_both,ec);

            if (ec && ec != boost::system::errc::not_connected){
               throw boost::system::system_error{ec};
            }

            return buffer_1.str();

        }
        catch(std::exception const& e)
        {
             std::stringstream buffer_2;
             buffer_2 << " Error: " << e.what() << std::endl;
             return buffer_2.str();
        }

        return "\p\n";
    }

    void
    create_response()
    {

        std::string str_1(request_.target());

        std::string str_2("/rtbdispatcher/rtb");
        std::string str_3("271");

        std::size_t found = str_1.find(str_2);
        std::size_t found_1 = str_1.find(str_3);

        if ((found!=std::string::npos) && (found_1!=std::string::npos))
        {
            response_.result(http::status::ok);
            response_.set(http::field::content_type, "text/plain");
            beast::ostream(response_.body()) << keenKaleIntegration();
        }

        else
        {
            response_.result(http::status::ok);
            response_.set(http::field::content_type, "text/plain");
            beast::ostream(response_.body()) << my_program_state::otherIntegration();
        }
    }

    void
    write_response()
    {
        auto self = shared_from_this();

        response_.set(http::field::content_length, response_.body().size());

        http::async_write(
            socket_,
            response_,
            [self](beast::error_code ec, std::size_t)
            {
                self->socket_.shutdown(tcp::socket::shutdown_send, ec);
                self->deadline_.cancel();
            });
    }

    void
    check_deadline()
    {
        auto self = shared_from_this();

        deadline_.async_wait(
            [self](beast::error_code ec)
            {
                if(!ec)
                {
                    self->socket_.close(ec);
                }
            });
    }
};

void
http_server(tcp::acceptor& acceptor, tcp::socket& socket)
{
  acceptor.async_accept(socket,
      [&](beast::error_code ec)
      {
          if(!ec)
              std::make_shared<http_connection>(std::move(socket))->start();
          http_server(acceptor, socket);
      });
}

int
main(int argc, char* argv[])
{
    try
    {

        argv[1]="0.0.0.0";
        argv[2]="8082";

        auto const address = net::ip::make_address(argv[1]);
        unsigned short port = static_cast<unsigned short>(std::atoi(argv[2]));

        net::io_context ioc{1};

        tcp::acceptor acceptor{ioc, {address, port}};
        tcp::socket socket{ioc};
        http_server(acceptor, socket);

        std::cout << " CREATED SERVER. LISTENER TO " << argv[1] << ":" << argv[2] << std::endl;

        ioc.run();
    }
    catch(std::exception const& e)
    {
        std::cerr << "Error: " << e.what() << std::endl;
        return EXIT_FAILURE;
    }
}


/*
#include "fields_alloc.hpp"

#include <boost/beast/core.hpp>
#include <boost/beast/http.hpp>
#include <boost/beast/version.hpp>
#include <boost/asio.hpp>
#include <boost/filesystem.hpp>
#include <chrono>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <list>
#include <memory>
#include <string>

#include <boost/property_tree/ptree.hpp>
#include <boost/property_tree/json_parser.hpp>

namespace beast = boost::beast;         // from <boost/beast.hpp>
namespace http = beast::http;           // from <boost/beast/http.hpp>
namespace net = boost::asio;            // from <boost/asio.hpp>


using tcp = boost::asio::ip::tcp;       // from <boost/asio/ip/tcp.hpp>

using boost::property_tree::ptree;
using boost::property_tree::read_json;
using boost::property_tree::write_json;



class http_worker
{
public:
    http_worker(http_worker const&) = delete;
    http_worker& operator=(http_worker const&) = delete;

    http_worker(tcp::acceptor& acceptor, const std::string& doc_root) :
        acceptor_(acceptor),
        doc_root_(doc_root)
    {
    }

    void start()
    {
        accept();
        check_deadline();
    }

private:
    using alloc_t = fields_alloc<char>;

    using request_body_t = http::string_body;

    tcp::acceptor& acceptor_;

    std::string doc_root_;

    tcp::socket socket_{acceptor_.get_executor().context()};

    beast::flat_static_buffer<8192> buffer_;

    alloc_t alloc_{8192};

    boost::optional<http::request_parser<request_body_t, alloc_t>> parser_;

    net::basic_waitable_timer<std::chrono::steady_clock> request_deadline_{
        acceptor_.get_executor().context(), (std::chrono::steady_clock::time_point::max)()};

    boost::optional<http::response<http::string_body, http::basic_fields<alloc_t>>> string_response_;

    boost::optional<http::response_serializer<http::string_body, http::basic_fields<alloc_t>>> string_serializer_;

    boost::optional<http::response<http::file_body, http::basic_fields<alloc_t>>> file_response_;

    boost::optional<http::response_serializer<http::file_body, http::basic_fields<alloc_t>>> file_serializer_;

    void accept()
    {
        beast::error_code ec;
        socket_.close(ec);
        buffer_.consume(buffer_.size());

        acceptor_.async_accept(
            socket_,
            [this](beast::error_code ec)
            {
                if (ec)
                {
                    accept();
                }
                else
                {
                    request_deadline_.expires_after(
                        std::chrono::seconds(60));

                    read_request();
                }
            });
    }

    void read_request()
    {

        parser_.emplace(
            std::piecewise_construct,
            std::make_tuple(),
            std::make_tuple(alloc_));

        http::async_read(
            socket_,
            buffer_,
            *parser_,
            [this](beast::error_code ec, std::size_t)
            {
                if (ec)
                    accept();
                else
                    process_request(parser_->get());
            });
    }

    void process_request(http::request<request_body_t, http::basic_fields<alloc_t>> const& req)
    {
        switch (req.method())
        {
        case http::verb::get:
            send_file(req.target());
            break;

        default:
            // We return responses indicating an error if
            // we do not recognize the request method.
            send_bad_response(
                http::status::bad_request,
                "Invalid request-method '" + std::string(req.method_string()) + "'\r\n");
            break;
        }
    }

    void send_bad_response(
        http::status status,
        std::string const& error)
    {
        string_response_.emplace(
            std::piecewise_construct,
            std::make_tuple(),
            std::make_tuple(alloc_));

        string_response_->result(status);
        string_response_->keep_alive(false);
        string_response_->set(http::field::server, "Beast");
        string_response_->set(http::field::content_type, "text/plain");
        string_response_->body() = error;
        string_response_->prepare_payload();

        string_serializer_.emplace(*string_response_);

        http::async_write(
            socket_,
            *string_serializer_,
            [this](beast::error_code ec, std::size_t)
            {
                socket_.shutdown(tcp::socket::shutdown_send, ec);
                string_serializer_.reset();
                string_response_.reset();
                accept();
            });
    }

    std::string otherIntegration()
    {
        return "Other integration not found\n";
    }


    std::string keenKaleIntegration(){

        try
        {
            std::string json_str("{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}");

            //ptree pt2;
            //std::istringstream is_1 (json_str);
            //read_json(is_1,pt2);
            //std::string json_str_1 = pt2.get<std::string>("id");

            auto const host = "rtb-useast.keenkale.com";
            auto const port = "80";
            auto const target = "/rtb?zone=55764";
            int version = 11;

            net::io_context ioc;
            tcp::resolver resolver{ioc};
            tcp::socket socket{ioc};

            auto const results = resolver.resolve(host,port);

            boost::asio::connect(socket, results.begin(), results.end());

            http::request<http::string_body> req{http::verb::post,target,version};

            req.set("Content-Type","application/json");
            req.set("Connection","Keep-Alive");
            req.set("Accept-Charset","UTF-8");
            req.set("Expect","x-openrtb-version: 2.4");
            req.set("x-forwarded-for","216.15.125.142");
            req.set("x-device-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            req.set("x-original-user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
            req.set(http::field::host,"rtb-useast.keenkale.com");
            req.set(http::field::user_agent,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");

            auto const json(json_str);

            req.body() = json;
            req.prepare_payload();

            http::write(socket,req);
            beast::flat_buffer buffer;

            http::response<http::dynamic_body> res;
            http::read(socket,buffer,res);

            std::stringstream buffer_1;
            buffer_1 << res << std::endl;

            boost::system::error_code ec;
            socket.shutdown(tcp::socket::shutdown_both,ec);

            if (ec && ec != boost::system::errc::not_connected){
               throw boost::system::system_error{ec};
            }

            return buffer_1.str();

        }
        catch(std::exception const& e)
        {
             std::stringstream buffer_2;
             buffer_2 << " Error: " << e.what() << std::endl;
             return buffer_2.str();
        }

        return "\p\n";
    }




    void send_file(beast::string_view target)
    {

        std::string str_1(target);

        std::string str_2("/rtbdispatcher/rtb");
        std::string str_3("271");

        std::size_t found = str_1.find(str_2);
        std::size_t found_1 = str_1.find(str_3);

        std::string response("");

        if ((found!=std::string::npos) && (found_1!=std::string::npos))
        {
            response = keenKaleIntegration();
        }

        else
        {
            response = otherIntegration();
        }

        auto const bodyResponse = response;


        string_response_.emplace(
            std::piecewise_construct,
            std::make_tuple(),
            std::make_tuple(alloc_));

        string_response_->result(http::status::ok);
        string_response_->keep_alive(false);
        string_response_->set(http::field::server, "Beast");
        string_response_->set(http::field::content_type, "text/plain");
        string_response_->body() = bodyResponse;
        string_response_->prepare_payload();

        string_serializer_.emplace(*string_response_);

        http::async_write(
            socket_,
            *string_serializer_,
            [this](beast::error_code ec, std::size_t)
            {
                socket_.shutdown(tcp::socket::shutdown_send, ec);
                string_serializer_.reset();
                string_response_.reset();
                accept();
            });
    }

    void check_deadline()
    {
        if (request_deadline_.expiry() <= std::chrono::steady_clock::now())
        {
            beast::error_code ec;
            socket_.close();

            request_deadline_.expires_at(
                std::chrono::steady_clock::time_point::max());
        }

        request_deadline_.async_wait(
            [this](beast::error_code)
            {
                check_deadline();
            });
    }
};

int main(int argc, char* argv[])
{
    try
    {


        argv[1] = "0.0.0.0";
        argv[2] = "8082";
        argv[3] = ".";
        argv[4] = "10";
        argv[5] = "spin";

        auto const address = net::ip::make_address(argv[1]);
        unsigned short port = static_cast<unsigned short>(std::atoi(argv[2]));
        std::string doc_root = argv[3];
        int num_workers = std::atoi(argv[4]);
        bool spin = (std::strcmp(argv[5], "spin") == 0);

        net::io_context ioc{1};
        tcp::acceptor acceptor{ioc, {address, port}};

        std::list<http_worker> workers;
        for (int i = 0; i < num_workers; ++i)
        {
            workers.emplace_back(acceptor, doc_root);
            workers.back().start();
        }

        if (spin)
          for (;;) ioc.poll();
        else
          ioc.run();

        std::cout << "SERVER LISTENER TO " << argv[1] << ":" << argv[2] << " " << std::endl;
    }
    catch (const std::exception& e)
    {
        std::cerr << "Error: " << e.what() << std::endl;
        return EXIT_FAILURE;
    }
}
*/









/*
#include "boost/filesystem.hpp"
#include "boost/foreach.hpp"
#include <iostream>

int main()
{
    boost::filesystem::path targetDir( "C:\\workspaces" );

    boost::filesystem::directory_iterator it( targetDir ), eod;

    BOOST_FOREACH( boost::filesystem::path const &p, std::make_pair( it, eod ) )
    {
        if( is_regular_file( p ) )
        {
            std::string filename = p.filename().string();
            std::cout << filename << std::endl;
        }
    }

    std::getchar();

    return 0;
}
*/
/*

#include <boost/format.hpp>
#include <boost/date_time.hpp>
#include <iostream>

int main()
{
    const boost::posix_time::ptime now = boost::posix_time::second_clock::local_time();

    const boost::wformat f = boost::wformat( L"%02d.%02d.%s  %02d:%02d" )
                    % now.date().year_month_day().day.as_number()
                    % now.date().year_month_day().month.as_number()
                    % now.date().year_month_day().year
                    % now.time_of_day().hours()
                    % now.time_of_day().minutes();

    std::wcout << f.str() << std::endl;
}
*/
