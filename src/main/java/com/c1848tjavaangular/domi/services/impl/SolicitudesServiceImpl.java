package com.c1848tjavaangular.domi.services.impl;

import java.util.List;

import com.c1848tjavaangular.domi.dtos.SolicitudesProfesionalDto;
import com.c1848tjavaangular.domi.dtos.SolicitudesUsuarioDto;
import com.c1848tjavaangular.domi.exceptions.AppNotFoundException;
import com.c1848tjavaangular.domi.mappers.SolicitudesMapper;
import com.c1848tjavaangular.domi.models.entities.Usuarios;
import com.c1848tjavaangular.domi.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import com.c1848tjavaangular.domi.dtos.SolicitudesDto;
import com.c1848tjavaangular.domi.models.entities.Solicitudes;
import com.c1848tjavaangular.domi.repository.SolicitudesRepository;
import com.c1848tjavaangular.domi.services.SolicitudesService;

import jakarta.transaction.Transactional;
@Service
public class SolicitudesServiceImpl implements SolicitudesService {

    private final SolicitudesRepository solicitudesRepository;
    private final SolicitudesMapper solicitudesMapper;
    private final UsuariosRepository usuariosRepository;

    public SolicitudesServiceImpl(SolicitudesRepository solicitudesRepository, SolicitudesMapper solicitudesMapper, UsuariosRepository usuariosRepository){
        this.solicitudesRepository = solicitudesRepository;
        this.solicitudesMapper = solicitudesMapper;
        this.usuariosRepository = usuariosRepository;
    }

    
    @Override
    public List<SolicitudesDto> findAll() {
        return solicitudesMapper.toSolicitudesDtos(solicitudesRepository.findAll());
    }

    @Transactional
    @Override
    public SolicitudesDto save(Integer idUsuario,SolicitudesDto solicitudesDto) {
        Usuarios usuario = usuariosRepository.findById(idUsuario)
                .orElseThrow(()-> new AppNotFoundException("Usuario not found!"));
        Solicitudes solicitudes = solicitudesMapper.toSolicitudes(solicitudesDto);
        solicitudes.setIdUsuario(usuario.getIdUsuarios());
        solicitudes.setEstado("Pendiente");
        return solicitudesMapper.toSolicitudesDto(solicitudesRepository.save(solicitudes));
    }

    @Transactional
    @Override
    public SolicitudesDto findById(Integer id) {
        Solicitudes solicitude = solicitudesRepository.findById(id)
                .orElseThrow(()-> new AppNotFoundException("Solicitud not found!"));
        
        return solicitudesMapper.toSolicitudesDto(solicitude);
    }

    @Override
    public SolicitudesDto update(Integer idSolicitud, SolicitudesDto solicitudesDto) {
        Solicitudes solicitude = solicitudesRepository.findById(idSolicitud)
                .orElseThrow(()-> new AppNotFoundException("Solicitud not found!"));
        solicitudesMapper.updateSolicitudes(solicitude, solicitudesDto);
        solicitude.setEstado("Terminada");
        return solicitudesMapper.toSolicitudesDto(solicitudesRepository.save(solicitude));
    }

    @Override
    public SolicitudesDto findByIdUsuario(Integer idUsuario) {
        return null;
    }

    @Transactional
    @Override
    public SolicitudesDto delete(Integer idSolicitud) {
        Solicitudes solicitude = solicitudesRepository.findById(idSolicitud)
                .orElseThrow(()-> new AppNotFoundException("Solicitud not found!"));
        SolicitudesDto solicitudesDto = solicitudesMapper.toSolicitudesDto(solicitude);
        solicitudesRepository.deleteById(idSolicitud);
        return solicitudesDto;
    }

    @Override
    public List<SolicitudesUsuarioDto> findSolicitudesByProfessionalId(Integer idUsuario) {
        return solicitudesRepository.findSolicitudesByProfessionalId(idUsuario);
    }

    @Override
    public List<SolicitudesUsuarioDto> findSolicitudesTerminadasByProfessionalId(Integer idUsuario) {
        return solicitudesRepository.findSolicitudesTerminadasByProfessionalId(idUsuario);
    }

    @Override
    public List<SolicitudesUsuarioDto> findSolicitudesPendientesByProfessionalId(Integer idUsuario) {
        return solicitudesRepository.findSolicitudesPendientesByProfessionalId(idUsuario);
    }

    @Override
    public List<SolicitudesProfesionalDto> findSolicitudesByUserId(Integer idUsuario) {
        return solicitudesRepository.findSolicitudesByUserId(idUsuario);
    }

    @Override
    public List<SolicitudesProfesionalDto> findSolicitudesTerminadasByUserId(Integer idUsuario) {
        return solicitudesRepository.findSolicitudesTerminadasByUserId(idUsuario);
    }

    @Override
    public List<SolicitudesProfesionalDto> findSolicitudesPendientesByUserId(Integer idUsuario) {
        return solicitudesRepository.findSolicitudesPendientesByUserId(idUsuario);
    }

}
