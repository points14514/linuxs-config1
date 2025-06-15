# WORKSPACE 文件示例
workspace(name = "my_project")

# BUILD 文件示例
cc_binary(
    name = "my_app",
    srcs = ["main.cpp"],
    deps = [":my_lib"],
)

cc_library(
    name = "my_lib",
    srcs = ["lib.cpp"],
    hdrs = ["lib.h"],
)
