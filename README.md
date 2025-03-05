# Inventory Manager

//TODO descripción del proyecto

## Set up

First, we need to create the volume where the data is going to be stored.

```console
foo@bar:~$ docker volume create pg_data
```

Then, we can create the docker container via the yaml, following the next command:

```console
foo@bar:~$ docker compose up -d
```

Note: the '-d' flag just dettach the execution from the terminal, but you can also run it without it.

This process will automatically create the database schema on your volume if you don't already have it.