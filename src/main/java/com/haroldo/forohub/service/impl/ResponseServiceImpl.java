package com.haroldo.forohub.service.impl;

import com.haroldo.forohub.persistence.entities.Response;
import com.haroldo.forohub.persistence.dao.IResponseDAO;
import com.haroldo.forohub.service.IResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseServiceImpl implements IResponseService {

    @Autowired
    private IResponseDAO responseDAO;


    @Override
    public List<Response> findAll() {
        return responseDAO.findAll();
    }

    @Override
    public Optional<Response> findById(Long id) {
        return responseDAO.findById(id);
    }

    @Override
    public void save(Response response) {
        responseDAO.save(response);

    }

    @Override
    public void deleteById(Long id) {
        responseDAO.deleteById(id);
    }
}
