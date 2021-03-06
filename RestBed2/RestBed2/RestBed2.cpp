// RestBed2.cpp : Este archivo contiene la función "main". La ejecución del programa comienza y termina ahí.
//

#include "pch.h"


#include <thread>

#include <memory>

#include <chrono>

#include <cstdlib>

#include <restbed>

#include <iostream>



using namespace std;

using namespace restbed;



std::string processKeenkaleIntegration() {

	//std::string response = "Keenkale found";

	try {
		auto tmp_request = std::make_shared<restbed::Request>(restbed::Uri("http://rtb-useast.keenkale.com/rtb?zone=55764"));
		//tmp_request->set_path("");
		tmp_request->set_method("POST");

		std::string content("{\"id\":\"dca01512ae9ce5b95712794dcd677d80\",\"imp\":[{\"id\":\"6bbc5bc3eb7081e1b7f4f7cc29b815f9\",\"instl\":0,\"tagid\":\"tx500674895\",\"bidfloor\":0.052500000000000005,\"bidfloorcur\":\"USD\",\"secure\":1,\"banner\":{\"id\":\"d06ca4a034793852b24a6bbc66e14690\",\"w\":320,\"h\":50,\"mimes\":[\"image/jpg\",\"image/gif\"],\"battr\":[],\"wmax\":320,\"hmax\":50,\"wmin\":300,\"hmin\":49,\"api\":[3,5]}}],\"device\":{\"ua\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36\",\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1},\"dnt\":0,\"lmt\":0,\"ip\":\"88.1.48.62\",\"devicetype\":4,\"make\":\"Samsung\",\"model\":\"gt-9300\",\"os\":\"Android\",\"osv\":\"4.4.4\",\"ifa\":\"1a58da58-4930-4adc-b1a4-2dc1ba386a96\",\"connectiontype\":2,\"js\":1,\"language\":\"es\"},\"test\":0,\"at\":1,\"tmax\":1500,\"badv\":[],\"app\":{\"id\":\"10308\",\"name\":\"Whatsdog\",\"publisher\":{\"id\":\"67fbd985230c665850075df702d12c5e\",\"name\":\"tappx\",\"domain\":\"tappx.com\"},\"bundle\":\"com.secondlemon.whatsdogpremium\",\"storeurl\":\"https://play.google.com/store/apps/details?id=com.secondlemon.whatsdogpremium\"},\"user\":{\"geo\":{\"country\":\"ESP\",\"lat\":41.427564,\"lon\":2.185005,\"type\":1}}}");

		tmp_request->set_body(content);
		tmp_request->set_header("Content-Length", std::to_string(content.size()));
		tmp_request->set_header("Content-Type", "application/json");
		tmp_request->set_header("Host", "rtb-useast.keenkale.com");
		tmp_request->set_header("Connection", "Keep-Alive");
		tmp_request->set_header("Accept-Charset", "UTF-8");
		tmp_request->set_header("Expect", "x-openrtb-version: 2.4");
		tmp_request->set_header("x-forwarded-for", "216.15.125.142");
		tmp_request->set_header("x-device-user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
		tmp_request->set_header("x-original-user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");


		auto tmp_response = restbed::Http::sync(tmp_request);
		auto length = tmp_response->get_header("Content-Length", 0);

		restbed::Http::fetch(length, tmp_response);
		
		std::stringstream buffer_2;
		buffer_2 << "keenkale integration code_status: " << tmp_response->get_status_code() << " body: " << std::endl;
		std::cout << buffer_2.str() << std::endl;
		std::string response = buffer_2.str();

		//response.append(reinterpret_cast<const char *>(tmp_response->get_body().data()), 50);
		return response;
	}
	catch (std::exception const& e)
	{
		std::stringstream buffer_2;
		buffer_2 << " Error: " << e.what() << std::endl;
		return buffer_2.str();
	}


}






void get_method_handler(const shared_ptr< Session > session)
{
	auto request = session->get_request();
	std::string key = request->get_path_parameter("id");
	
	std::string message = "";

	
	std::cout << "rtb/dispatcher id: " << key.c_str() << std::endl;

	std::string str_2("271");
	std::size_t found = key.find(str_2);


	if (found != std::string::npos)
	{
		message = processKeenkaleIntegration();
	}
	else 
	{
		std::stringstream buffer_2;
		buffer_2 << " Integration: " << key << " Not found " << std::endl;
		message = buffer_2.str();
	}
	
	std::string length_message = std::to_string(message.length());

	session->close(OK, message, { { "Content-Length", length_message}, { "Connection", "close" } });
}



void service_ready_handler(Service&)

{
	fprintf(stderr, "SERVICE RUNNING AT LOCALHOST:8082");
	std::cout << "\n" << std::endl;
}



int main(const int, const char**)

{

	auto resource = make_shared< Resource >();

	resource->set_path("/rtbdispatcher/rtb/{id: .*}");

	resource->set_method_handler("GET", get_method_handler);



	auto settings = make_shared< Settings >();

	settings->set_port(8082);



	auto service = make_shared< Service >();

	service->publish(resource);

	service->set_ready_handler(service_ready_handler);

	service->start(settings);



	return EXIT_SUCCESS;

}




/*
#include <memory>

#include <cstdlib>

#include <restbed>



using namespace std;

using namespace restbed;



void post_method_handler(const shared_ptr< Session > session)

{

	const auto request = session->get_request();



	int content_length = request->get_header("Content-Length", 0);



	session->fetch(content_length, [](const shared_ptr< Session > session, const Bytes & body)

	{

		fprintf(stdout, "%.*s\n", (int)body.size(), body.data());

		session->close(OK, "Hello, World!", { { "Content-Length", "13" } });

	});

}




int main(const int, const char**)

{

	auto resource = make_shared< Resource >();

	resource->set_path("/resource");

	resource->set_method_handler("POST", post_method_handler);



	auto settings = make_shared< Settings >();

	settings->set_port(1984);

	settings->set_default_header("Connection", "close");



	Service service;

	service.publish(resource);

	service.start(settings);



	return EXIT_SUCCESS;

}
*/



/*
#include <iostream>

int main()
{
    std::cout << "Hello World!\n"; 
}

// Ejecutar programa: Ctrl + F5 o menú Depurar > Iniciar sin depurar
// Depurar programa: F5 o menú Depurar > Iniciar depuración

// Sugerencias para primeros pasos: 1. Use la ventana del Explorador de soluciones para agregar y administrar archivos
//   2. Use la ventana de Team Explorer para conectar con el control de código fuente
//   3. Use la ventana de salida para ver la salida de compilación y otros mensajes
//   4. Use la ventana Lista de errores para ver los errores
//   5. Vaya a Proyecto > Agregar nuevo elemento para crear nuevos archivos de código, o a Proyecto > Agregar elemento existente para agregar archivos de código existentes al proyecto
//   6. En el futuro, para volver a abrir este proyecto, vaya a Archivo > Abrir > Proyecto y seleccione el archivo .sln
*/