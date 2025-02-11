package com.example.account.Service;

import com.example.account.DAO.*;
import com.example.account.DTO.DTOCliente;
import com.example.account.DTO.DTOPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<DTOCliente> getClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertirToDTO)
                .collect(Collectors.toList());
    }

    public DTOCliente getClienteById(Long clienteId) {
        Optional<DAOCliente> cliente = clienteRepository.findById(clienteId);
        return cliente.map(this::convertirToDTO).orElse(null);
    }

    public DTOCliente registrarCliente(DTOCliente dtoCliente) {
        DAOPersona daoPersona = new DAOPersona();
        daoPersona.setNombre(dtoCliente.getPersona().getNombre());
        daoPersona.setIdentificacion(dtoCliente.getPersona().getIdentificacion());
        daoPersona.setDireccion(dtoCliente.getPersona().getDireccion());
        daoPersona.setTelefono(dtoCliente.getPersona().getTelefono());
        // guardar persona en la base de datos
        DAOPersona savedPersona = personaRepository.save(daoPersona);


        DAOCliente daoCliente = new DAOCliente();
        daoCliente.setPersona(savedPersona); // link a la persona
        daoCliente.setEdad(dtoCliente.getEdad());
        daoCliente.setGenero(dtoCliente.getGenero());
        daoCliente.setPassword(dtoCliente.getPassword());
        daoCliente.setEstado(dtoCliente.getEstado());

        // guardar el cliente en la BD y obtener el ID generado
        DAOCliente savedCliente = clienteRepository.save(daoCliente);
        DTOCliente responseDTO = convertirToDTO(savedCliente);
        // Asignar el ID generado
        responseDTO.setId(savedCliente.getId());
        return responseDTO;
    }

    private DTOCliente convertirToDTO(DAOCliente cliente) {
        //settear los objetos persona y cliente
        DTOCliente dtoCliente = new DTOCliente();
        dtoCliente.setId(cliente.getId());
        dtoCliente.setPassword(cliente.getPassword());
        dtoCliente.setEstado(cliente.getEstado());
        dtoCliente.setEdad(cliente.getEdad());
        dtoCliente.setGenero(cliente.getGenero());

        DTOPersona dtoPersona = new DTOPersona();
        dtoPersona.setId(cliente.getPersona().getId());
        dtoPersona.setNombre(cliente.getPersona().getNombre());
        dtoPersona.setIdentificacion(cliente.getPersona().getIdentificacion());
        dtoPersona.setDireccion(cliente.getPersona().getDireccion());
        dtoPersona.setTelefono(cliente.getPersona().getTelefono());
        dtoCliente.setPersona(dtoPersona);

        return dtoCliente;
    }

    public DTOCliente updateCliente(Long id, DTOCliente dtoCliente) {
        Optional<DAOCliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            DAOCliente clienteExistente = optionalCliente.get();

            // Actualizar datos de la persona
            DAOPersona persona = clienteExistente.getPersona();
            persona.setNombre(dtoCliente.getPersona().getNombre());
            persona.setIdentificacion(dtoCliente.getPersona().getIdentificacion());
            persona.setDireccion(dtoCliente.getPersona().getDireccion());
            persona.setTelefono(dtoCliente.getPersona().getTelefono());
            personaRepository.save(persona);

            // Actualizar datos del cliente
            clienteExistente.setEdad(dtoCliente.getEdad());
            clienteExistente.setGenero(dtoCliente.getGenero());
            clienteExistente.setPassword(dtoCliente.getPassword());
            clienteExistente.setEstado(dtoCliente.getEstado());
            clienteRepository.save(clienteExistente);

            return convertirToDTO(clienteExistente);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Transactional
    public void deleteCliente(Long id) {
        Optional<DAOCliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty())
        {
            throw new RuntimeException("Cliente no encontrado.");
        }
        DAOCliente cliente = clienteOptional.get();
        if (!cliente.getCuentas().isEmpty())
        {
            throw new RuntimeException("No se puede eliminar el cliente porque tiene cuentas asociadas.");
        }
        // Guardar referencia de la persona antes de eliminar el cliente
        DAOPersona persona = cliente.getPersona();
        clienteRepository.delete(cliente);
        clienteRepository.flush();
        System.out.println("Cliente eliminado con éxito: " + id);
    }

}
