# 定义编译器
CC = gcc

# 定义目标文件
TARGET = myprogram

# 定义源文件
SOURCES = main.c utils.c
OBJECTS = $(SOURCES:.c=.o)

# 定义头文件
HEADERS = utils.h

# 定义编译选项
CFLAGS = -Wall -g

# 默认目标
all: $(TARGET)

# 编译目标文件
$(TARGET): $(OBJECTS)
	$(CC) $(OBJECTS) -o $(TARGET)

# 编译源文件生成目标文件
%.o: %.c $(HEADERS)
	$(CC) $(CFLAGS) -c $< -o $@

# 清理目标（不包含删除文件的命令）
clean:
	@echo "No files to delete."

# 安装目标（可选）
install: $(TARGET)
	cp $(TARGET) /usr/local/bin/

# 卸载目标（可选）
uninstall:
	rm -f /usr/local/bin/$(TARGET)
