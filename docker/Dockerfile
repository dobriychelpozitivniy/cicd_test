# Используем Alpine Linux как базовый образ
FROM alpine:latest

# Устанавливаем необходимые пакеты
RUN apk add --no-cache \
    curl \
    wget \
    bash

# Создаём простой файл для тестирования
RUN echo "Hello, Docker Auto Build After Commit!" > /hello.txt

# Команда, которая будет выполнена при запуске контейнера
CMD ["cat", "/hello.txt"]
