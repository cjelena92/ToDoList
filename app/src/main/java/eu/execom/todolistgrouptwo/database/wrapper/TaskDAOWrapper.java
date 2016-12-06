package eu.execom.todolistgrouptwo.database.wrapper;


import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

import eu.execom.todolistgrouptwo.api.RestApi;

import eu.execom.todolistgrouptwo.model.Task;


@EBean
public class TaskDAOWrapper {

    @RestService
    RestApi restApi;



    public Task create(Task task) {

        return restApi.createTask(task);
    }

}
