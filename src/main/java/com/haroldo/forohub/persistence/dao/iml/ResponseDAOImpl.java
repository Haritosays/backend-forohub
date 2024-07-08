package com.haroldo.forohub.persistence.dao.iml;

import com.haroldo.forohub.persistence.entities.Response;
import com.haroldo.forohub.persistence.repository.ResponseRepository;
import com.haroldo.forohub.persistence.dao.IResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResponseDAOImpl implements IResponseDAO {

    @Autowired
    private ResponseRepository responseRepository;

    @Override
    public List<Response> findAll() {
        return (List<Response>) responseRepository.findAll();
    }

    @Override
    public Optional<Response> findById(Long id) {
        return responseRepository.findById(id);
    }

    @Override
    public void save(Response response) {
        responseRepository.save(response);
    }

    @Override
    public void deleteById(Long id) {
        responseRepository.deleteById(id);
    }
}
