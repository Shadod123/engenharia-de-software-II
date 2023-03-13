package service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import model.Contato;
import model.DbOperations;
import org.apache.log4j.Logger;
import spark.Request;
import spark.Response;

public class ContatoService {
    public ContatoService() {}

    public Object get(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
		Contato contato = DbOperations.findRecordById(id);

		if(contato == null) {
            System.out.println("Erro: Contato não existente!");
            response.status(404);
            return "Erro: Contato não existente!";
		}

        response.status(200); // 200 Succeded
        return contato;
    }

    public Object add(Request request, Response response) {
        String nome = request.queryParams("nome");
        String endereco = request.queryParams("endereco");
        String telefone = request.queryParams("telefone");

		int id = DbOperations.createRecord(nome, endereco, telefone);

        response.status(201); // 201 Created
        return "Contato criado com sucesso! ID: " + id;
    }

    public Object update(Request request, Response response){
        int id = Integer.parseInt(request.queryParams("codigo"));
        String novoNome = request.queryParams("nome");
        String novoEndereco = request.queryParams("endereco");
        String novoTelefone = request.queryParams("telefone");

        Contato contato = DbOperations.findRecordById(id);

        if(contato == null) {
            System.out.println("Erro: Contato não existente!");
            response.status(404);
            return "Erro: Contato não existente!";
        }

        id = DbOperations.updateRecord(id, novoNome, novoEndereco, novoTelefone);

        response.status(201); // 201 Created
        return "Contato atualizado com sucesso!";
    }

    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.queryParams("codigo"));

        Contato contato = DbOperations.findRecordById(id);

        if(contato == null) {
            System.out.println("Erro: Contato não existente!");
            response.status(404);
            return "Erro: Contato não existente!";
        }

        Contato contatoDeletado = (Contato) DbOperations.deleteRecord(id);

        response.status(200); // 200 Succeded
        return "Contato removido com sucesso!";
    }
}
