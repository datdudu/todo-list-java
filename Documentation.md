# Documentação das classes do projeto
## URL

```shell
http://localhost:8080/api/v1/
```
## Controller

| Método                             | Endpoint       | Descrição                                                                 | Respostas                                                                                     |
|------------------------------------|----------------|---------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| POST                               | `/tasks`       | Cria uma nova tarefa.                                                     | - 201: Tarefa criada com sucesso. Retorna os dados da tarefa criada.<br>- 400: Alguns dos campos estão inválidos.    |
| GET                                | `/tasks`       | Obtém todas as tarefas.                                                   | - 200: Lista de tarefas obtida com sucesso. Retorna um array contendo as tarefas.<br>- 404: Nenhuma tarefa encontrada. |
| GET                                | `/tasks/{id}`  | Obtém uma tarefa pelo ID.                                                 | - 200: Tarefa encontrada. Retorna os dados da tarefa.<br>- 404: Tarefa não encontrada.                  |
| PUT                                | `/tasks/{id}`  | Atualiza uma tarefa pelo ID.                                              | - 200: Tarefa atualizada com sucesso. Retorna os dados da tarefa atualizada.<br>- 400: Requisição inválida.<br>- 404: Tarefa não encontrada.     |
| DELETE                             | `/tasks/{id}`  | Deleta uma tarefa pelo ID.                                                | - 204: Tarefa deletada com sucesso.<br>- 404: Tarefa não encontrada.                               |

#### Criar uma nova Task

| Método  | Endpoint  | Descrição              | Respostas                                                                                              |
|---------|-----------|------------------------|--------------------------------------------------------------------------------------------------------|
| POST    | `/tasks`  | Cria uma nova tarefa.  | - 201: Tarefa criada com sucesso. Retorna os dados da tarefa criada.<br>- 400: Alguns campos inválidos.|

#### Obter todas as Tasks

| Método  | Endpoint  | Descrição                  | Respostas                                                                                                      |
|---------|-----------|----------------------------|----------------------------------------------------------------------------------------------------------------|
| GET     | `/tasks`  | Obtém todas as tarefas.   | - 200: Lista de tarefas obtida com sucesso. Retorna um array contendo as tarefas.<br>- 404: Nenhuma tarefa encontrada. |

#### Obter uma Task por ID

| Método  | Endpoint         | Descrição                      | Respostas                                                                              |
|---------|------------------|--------------------------------|----------------------------------------------------------------------------------------|
| GET     | `/tasks/{id}`    | Obtém uma tarefa pelo ID.     | - 200: Tarefa encontrada. Retorna os dados da tarefa.<br>- 404: Tarefa não encontrada. |

#### Atualizar uma Task por ID

| Método  | Endpoint         | Descrição                            | Respostas                                                                                      |
|---------|------------------|--------------------------------------|------------------------------------------------------------------------------------------------|
| PUT     | `/tasks/{id}`    | Atualiza uma tarefa pelo ID.         | - 200: Tarefa atualizada com sucesso. Retorna os dados da tarefa atualizada.<br>- 400: Requisição inválida.<br>- 404: Tarefa não encontrada. |

#### Deletar uma Task por ID

| Método  | Endpoint         | Descrição                          | Respostas                                                                                 |
|---------|------------------|------------------------------------|-------------------------------------------------------------------------------------------|
| DELETE  | `/tasks/{id}`    | Deleta uma tarefa pelo ID.        | - 204: Tarefa deletada com sucesso.<br>- 404: Tarefa não encontrada.                     |


## Model

| Campo             | Tipo           | Descrição                                 |
|-------------------|----------------|-------------------------------------------|
| id                | Long           | Identificador único da tarefa.            |
| title             | String         | Título da tarefa.                        |
| description       | String         | Descrição da tarefa.                     |
| deadline          | LocalDateTime  | Prazo para conclusão da tarefa.          |
| createdAt         | LocalDateTime  | Data e hora de criação da tarefa.        |
| updatedAt         | LocalDateTime  | Data e hora da última atualização.       |

## Service

| Método                                    | Descrição                                           |
|-------------------------------------------|-----------------------------------------------------|
| `createTask(Task task)`                  | Cria uma nova tarefa.                               |
| `listAllTasks()`                         | Lista todas as tarefas.                             |
| `findTaskById(Long id)`                  | Encontra uma tarefa pelo ID.                        |
| `updateTaskById(Task task, Long id)`     | Atualiza uma tarefa pelo ID.                        |
| `deleteById(Long id)`                    | Deleta uma tarefa pelo ID.                          |
