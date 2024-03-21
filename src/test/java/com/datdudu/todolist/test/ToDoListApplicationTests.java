package com.datdudu.todolist.test;

import com.datdudu.todolist.controller.TaskController;
import com.datdudu.todolist.model.Task;
import com.datdudu.todolist.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoListApplicationTests {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
    }

    @Test
    public void createTask_Success() {
        // Arrange
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        // Act
        Task createdTask = taskController.createTask(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals(task, createdTask);
    }

    @Test
    public void createTask_InvalidInput() {
        // Arrange
        Task task = null;

        // Act
        Task createdTask = taskController.createTask(task);

        // Assert
        assertNull(createdTask);
    }

    @Test
    public void getAllTasks_Success() {
        // Arrange
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(taskService.listAllTasks()).thenReturn(tasks);

        // Act
        List<Task> response = taskController.getAllTasks();

        // Assert
        assertEquals(tasks, response);
    }

    @Test
    public void getAllTasks_NoTasksFound() {
        // Arrange
        when(taskService.listAllTasks()).thenReturn(new ArrayList<>());

        // Act
        List<Task> response = taskController.getAllTasks();

        // Assert
        assertNotNull(response);
        assertEquals(0, response.size());
    }

    @Test
    public void getTaskById_Success() {
        // Arrange
        Long id = 1L;
        when(taskService.findTaskById(id)).thenReturn(ResponseEntity.ok(task));

        // Act
        ResponseEntity<Task> response = taskController.getTaskByid(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    public void getTaskById_TaskNotFound() {
        // Arrange
        Long id = 1L;
        when(taskService.findTaskById(id)).thenReturn(ResponseEntity.notFound().build());

        // Act
        ResponseEntity<Task> response = taskController.getTaskByid(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateTaskById_Success() {
        // Arrange
        Long id = 1L;
        when(taskService.updateTaskById(any(Task.class), eq(id))).thenReturn(ResponseEntity.ok(task));

        // Act
        ResponseEntity<Task> response = taskController.updateTaskById(id, task);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(task, response.getBody());
    }

    @Test
    public void updateTaskById_InvalidInput() {
        // Arrange
        Long id = 1L;
        Task task = new Task();
        when(taskService.updateTaskById(task, id)).thenReturn(ResponseEntity.badRequest().build());

        // Act
        ResponseEntity<Task> response = taskController.updateTaskById(id, task);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode()); // Verifica se a resposta é BAD_REQUEST
    }

    @Test
    public void deleteTaskById_Success() {
        // Arrange
        Long id = 1L;
        when(taskService.deleteById(id)).thenReturn(ResponseEntity.noContent().build());

        // Act
        ResponseEntity<Object> response = taskController.deleteTaskById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteTaskById_TaskNotFound() {
        // Arrange
        Long id = 1L;
        when(taskService.deleteById(id)).thenReturn(ResponseEntity.notFound().build());

        // Act
        ResponseEntity<Object> response = taskController.deleteTaskById(id);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
