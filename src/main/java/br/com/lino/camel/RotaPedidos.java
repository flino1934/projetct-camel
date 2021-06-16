package br.com.lino.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaPedidos {

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {

				from("file:pedidos?delay=5s&noop=true") // de
					//.log("${id}")// vai passar as informações do log da msg ("dado que foi enviado")
					//.log("${body}")//vai mostrar o corpo da msg que esta sendo transitada
					.log("${id} - ${body}")	
					.marshal().xmljson()//vai transformar de xml para json
					.log("${body}")
					.setHeader("CamelFileName", simple("${file:name}.json"))//vai mudar o nome do arquivo
						.to("file:saida");// para

			}
		});

        context.start(); //aqui camel realmente começa a trabalhar
        System.out.println("passou");
        Thread.sleep(20000); //esperando um pouco para dar um tempo para camel
        System.out.println("terminou");
    }    
}
  
        
        
