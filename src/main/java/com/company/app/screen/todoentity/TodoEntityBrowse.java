package com.company.app.screen.todoentity;

import com.company.app.service.TodoService;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.app.entity.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("TodoEntity.browse")
@UiDescriptor("todo-entity-browse.xml")
@LookupComponent("todoEntitiesTable")
public class TodoEntityBrowse extends StandardLookup<TodoEntity> {

    @Autowired
    private TodoService todoService;
    @Autowired
    private CollectionContainer<TodoEntity> todoEntitiesDc;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        List<TodoEntity> todos = todoService.loadTodos();
        todoEntitiesDc.setItems(todos);
    }
}