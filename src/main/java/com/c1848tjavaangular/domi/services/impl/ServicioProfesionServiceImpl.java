package com.c1848tjavaangular.domi.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.c1848tjavaangular.domi.dtos.ProfesionalDto;
import com.c1848tjavaangular.domi.dtos.ServiciosProfesionalDto;
import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.mappers.ServicioProfesionMapper;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import com.c1848tjavaangular.domi.repository.UsuariosRepository;

import org.springframework.stereotype.Service;

import com.c1848tjavaangular.domi.dtos.ServicioProfesionDto;
import com.c1848tjavaangular.domi.models.entities.ServicioProfesion;
import com.c1848tjavaangular.domi.repository.ServicioProfesionRepository;
import com.c1848tjavaangular.domi.services.ServicioProfesionService;
@Service
public class ServicioProfesionServiceImpl implements ServicioProfesionService{
    private final ServicioProfesionRepository servicioProfesionRepository;
    private final ServicioProfesionMapper servicioProfesionMapper;
    private final UsuariosRepository usuariosRepository;

    public ServicioProfesionServiceImpl(ServicioProfesionRepository servicioProfesionRepository, ServicioProfesionMapper servicioProfesionMapper, UsuariosRepository usuariosRepository){
        this.servicioProfesionRepository = servicioProfesionRepository;
        this.servicioProfesionMapper = servicioProfesionMapper;
        this.usuariosRepository = usuariosRepository;
    }


    @Override
    public List<ProfesionalDto> getProfesionales() {
        List<Object[]> rawData = servicioProfesionRepository.findAllProfesionales();
        List<ProfesionalDto> result = new ArrayList<>();

        for (Object[] row : rawData) {
            Integer idUsuario = ((Number) row[0]).intValue();
            String nombre = (String) row[1];
            String apellidos = (String) row[2];
            String direccion = (String) row[3];
            String fotoPerfil = (String) row[4];
            String fotoPortada = (String) row[5];
            String servicios = (String) row[6];

            ProfesionalDto dto = new ProfesionalDto(idUsuario, nombre, apellidos, direccion, fotoPerfil, fotoPortada, servicios);
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<ServiciosProfesionalDto> getServiciosProfesionByIdUsuario(Integer idUsuario) {
        return servicioProfesionRepository.findAllServiciosProfesionByIdUsuario(idUsuario);
    }

    @Override
    public ServicioProfesionDto save(Integer idUsuario,ServicioProfesionDto servicioProfesionDto) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(()-> new AppNotFoundException("Usuario not found!"));
        ServicioProfesion servicioProfesion = servicioProfesionMapper.toServicioProfesion(servicioProfesionDto);
        servicioProfesion.setIdUsuario(usuario.getIdUsuarios());
        return servicioProfesionMapper.toServicioProfesionDto(servicioProfesionRepository.save(servicioProfesion));
    }

    @Override
    public ServicioProfesionDto findById(Integer id) {
        ServicioProfesion servicioProfesion = servicioProfesionRepository.findById(id)
                .orElseThrow(()-> new AppNotFoundException("Servicio not found"));
        return servicioProfesionMapper.toServicioProfesionDto(servicioProfesion);
    }

    @Override
    public List<ProfesionalDto> getProfesionalesByNombreServicio(String nombreServicio) {
        List<Object[]> rawData = servicioProfesionRepository.findProfesionalesByNombreServicio(nombreServicio);
        List<ProfesionalDto> result = new ArrayList<>();

        for (Object[] row : rawData) {
            Integer idUsuario = ((Number) row[0]).intValue();
            String nombre = (String) row[1];
            String apellidos = (String) row[2];
            String direccion = (String) row[3];
            String fotoPerfil = (String) row[4];
            String fotoPortada = (String) row[5];
            String servicios = (String) row[6];

            ProfesionalDto dto = new ProfesionalDto(idUsuario, nombre, apellidos, direccion, fotoPerfil, fotoPortada, servicios);
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<ProfesionalDto> getProfesionalByNombreServicioAndDireccion(String nombreServicio, String direccionProfesional) {
        List<Object[]> rawData = servicioProfesionRepository.findProfesionalByNombreServicioAndDireccion(nombreServicio, direccionProfesional);
        List<ProfesionalDto> result = new ArrayList<>();

        for (Object[] row : rawData) {
            Integer idUsuario = ((Number) row[0]).intValue();
            String nombre = (String) row[1];
            String apellidos = (String) row[2];
            String direccion = (String) row[3];
            String fotoPerfil = (String) row[4];
            String fotoPortada = (String) row[5];
            String servicios = (String) row[6];

            ProfesionalDto dto = new ProfesionalDto(idUsuario, nombre, apellidos, direccion, fotoPerfil, fotoPortada, servicios);
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<ProfesionalDto> getProfesionalByDireccion(String direccionProfesional) {
        List<Object[]> rawData = servicioProfesionRepository.findProfesionalByDireccion(direccionProfesional);
        List<ProfesionalDto> result = new ArrayList<>();

        for (Object[] row : rawData) {
            Integer idUsuario = ((Number) row[0]).intValue();
            String nombre = (String) row[1];
            String apellidos = (String) row[2];
            String direccion = (String) row[3];
            String fotoPerfil = (String) row[4];
            String fotoPortada = (String) row[5];
            String servicios = (String) row[6];

            ProfesionalDto dto = new ProfesionalDto(idUsuario, nombre, apellidos, direccion, fotoPerfil, fotoPortada, servicios);
            result.add(dto);
        }

        return result;
    }

    @Override
    public ServiciosProfesionalDto getServicioProfesionalById(Integer id) {
        return servicioProfesionRepository.findServicioProfesionalById(id);
    }

    @Override
    public ServicioProfesionDto delete(Integer id) {
        ServicioProfesion servicioProfesion = servicioProfesionRepository.findById(id)
                .orElseThrow(()-> new AppNotFoundException("Servicio not found!"));
        ServicioProfesionDto servicioProfesionDto = servicioProfesionMapper.toServicioProfesionDto(servicioProfesion);
        servicioProfesionRepository.deleteById(id);
        return servicioProfesionDto;
    }

    @Override
    public String getEmailUsuario(Integer idServicioP) {
        return servicioProfesionRepository.emailByIdServicioProfesional(idServicioP);
    }

    @Override
    public String getUsername(Integer idServicioP) {
        return servicioProfesionRepository.usernameByIdServicioProfesional(idServicioP);
    }
}
