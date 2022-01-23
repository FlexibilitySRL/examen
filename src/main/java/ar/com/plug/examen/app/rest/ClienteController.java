package ar.com.plug.examen.app.rest;


import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.domain.model.ClienteModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping()
    public ArrayList<ClienteModel> obtenerClientes(){
        ArrayList<ClienteModel> resultado = clienteService.findClientes();
        if(resultado != null){
            System.out.println("¡Consulta Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta");
        }
        return resultado;
    }

    @PostMapping()
    public ClienteModel registrarCliente(RequestBody ClienteModel clienteModel){
        ClienteModel resultado = this.clienteService.insertClient(clienteModel);
        if(resultado != null){
            System.out.println("¡Ha sido registrado correctamente!");
        }else{
            System.out.println("Los datos no fueron registrados");
        }
        return resultado;

    }

    @GetMapping(path = "/{id}")
    public Optional<ClienteModel> buscarClienteId(@PathVariable("id") Long id){
        ClienteModel resultado = clienteService.findId(id);
        if(resultado != null){
            System.out.println("¡Consulta por Id Eficiente!");
        }else{
            System.out.println("Hubo un error en la consulta por Id");
        }
        return resultado;

    }

    @PutMapping(path = "/{id}")
    public String updateCliente(@PathVariable("id") Long id, 
        RequestBody ClienteModel clienteModelForm){

        ClienteModel clienteModel = clienteService.findId(id);
        clienteModel.setEstatus(clienteModelForm.getEstatus());

        clienteService.insertClient(clienteModel);

        if(clienteModel != null){
            System.out.println("Se ha actualizado correctamente");
            return "Actualizdo el id " + id;
        }else{
            System.out.println("No se pudo actualizar el estatus del cliente");
            return "No se actualizo el estatus del id cliente " + id;
        }
    }
}
