cmake_minimum_required(VERSION 3.22)
project(x-translate-core)

# C++ standard
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Compiler flags
if(NOT CMAKE_BUILD_TYPE)
  set(CMAKE_BUILD_TYPE Release)
endif()

# Find ONNX Runtime (should be in Android SDK)
find_package(ONNX REQUIRED HINTS "${CMAKE_CURRENT_SOURCE_DIR}/../core/src/main/cpp")

# Source files
file(GLOB_RECURSE CORE_SOURCES
  "android/core/src/main/cpp/src/*.cpp"
)

file(GLOB_RECURSE CORE_HEADERS
  "android/core/src/main/cpp/include/*.h"
)

# Create shared library for JNI
add_library(x-translate-core SHARED
  ${CORE_SOURCES}
  ${CORE_HEADERS}
)

# Include directories
target_include_directories(x-translate-core PRIVATE
  android/core/src/main/cpp/include
  ${ONNX_INCLUDE_DIRS}
)

# Compiler options for optimization and security
if(CMAKE_BUILD_TYPE STREQUAL "Release")
  target_compile_options(x-translate-core PRIVATE
    -O3
    -flto
    -fstack-protector-strong
    -ffunction-sections
    -fdata-sections
  )
  target_link_options(x-translate-core PRIVATE
    -Wl,--gc-sections
    -Wl,-s
  )
endif()

# Link libraries
target_link_libraries(x-translate-core PRIVATE
  log
  android
  ${ONNX_LIBRARIES}
)

# Strip symbols in release build
if(CMAKE_BUILD_TYPE STREQUAL "Release")
  add_custom_command(TARGET x-translate-core POST_BUILD
    COMMAND ${CMAKE_STRIP} libx-translate-core.so
    WORKING_DIRECTORY ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}
  )
endif()

message(STATUS "x-translate-core library configured")
message(STATUS "Build type: ${CMAKE_BUILD_TYPE}")
message(STATUS "ONNX Runtime path: ${ONNX_INCLUDE_DIRS}")
