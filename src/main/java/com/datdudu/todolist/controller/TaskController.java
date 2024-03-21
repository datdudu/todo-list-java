package com.datdudu.todolist.controller;

import com.datdudu.todolist.model.Task;
import com.datdudu.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @Operation(summary = "Criar uma nova Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task criada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Alguns dos campos está inválido",
                    content = @Content)}
    )
    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        log.info("Criando uma Task com essas informações [{}]", task);
        return taskService.createTask(task);
    }

    @Operation(summary = "Obter todas as Tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Tasks obtida com sucesso",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Task.class))) }),
            @ApiResponse(responseCode = "404", description = "Nenhuma Task encontrada",
                    content = @Content)})
    @GetMapping("tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAllTasks(){
        log.info("Listando todas as Tasks");

        return taskService.listAllTasks();
    }

    @Operation(summary = "Obter uma Task por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task encontrada",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "404", description = "Task não encontrada",
                    content = @Content)})
    @GetMapping("tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskByid(@PathVariable (value = "id") Long id) {
        log.info("Buscando com o id [{}]", id);
        return taskService.findTaskById(id);
    }

    @Operation(summary = "Atualizar uma Task por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task atualizada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Task.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Task não encontrada",
                    content = @Content)})
    @PutMapping("tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task) {
        log.info("Atualizando a task com o id [{}] e informações novas [{}]", id, task);
        return taskService.updateTaskById(task,id);
    }

    @Operation(summary = "Deletar uma Task por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Task deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Task não encontrada",
                    content = @Content)})
    @DeleteMapping("tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id) {
        log.info("Deletando com o id [{}]", id);
        return taskService.deleteById(id);
    }
}
