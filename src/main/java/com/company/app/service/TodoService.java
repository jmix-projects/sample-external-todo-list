package com.company.app.service;

import com.company.app.entity.TodoEntity;
import com.company.app.external.ExternalTodoService;
import io.jmix.core.Metadata;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService {

    @Autowired
    private ExternalTodoService externalTodoService;
    @Autowired
    private Metadata metadata;
    @Autowired
    private ModelMapper modelMapper; // this bean is defined in main app class

    public List<TodoEntity> loadTodos() {
        return externalTodoService.loadTodos().stream()
                .map(todo -> {
                    TodoEntity todoEntity = metadata.create(TodoEntity.class);
                    modelMapper.map(todo, todoEntity);
                    return todoEntity;
                })
                .collect(Collectors.toList());
    }
}
