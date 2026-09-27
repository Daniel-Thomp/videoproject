# Terminal Video Player

A Java-based application that processes video input and renders it directly into the terminal using text characters. Built to handle multiple display formats, this project transforms standard video files into ASCII/text-based terminal animations.

## Features

*   **Terminal Rendering:** Plays back video directly in standard command-line interfaces.
*   **Multiple Rendering Modes:** 
    *   **Binary/Text Mode:** Maps each pixel to either white or black and then replaces those colours with characters of the user's choosing or from a text file.
    *   **Color Mode:** Utilizes ANSI escape codes to render colored video frames in the terminal.
*   **Maven Integration:** Standardized build and dependency management.

## Project Structure

The core logic is divided into modular Java classes located in `com.daniel`:

*   `Main.java`: The primary entry point for the application.
*   `VideoProcess.java`: Handles the extraction, reading, and processing of video frames.
*   `BinaryVideo.java` / `BinaryVideoText.java`: Responsible for mapping video pixels to monochrome text characters.
*   `ColourVideo.java`: Handles the translation of video pixels to colored terminal output.
*   `Utils.java`: Contains shared helper functions and utilities for terminal manipulation and math.
*   `resources/BadApple!!.mp4`: The legendary benchmark video included for immediate testing.

## Prerequisites

To build and run this project, you will need:
*   **Java Development Kit (JDK):** Version 8 or higher.
*   **Apache Maven:** For building the project and managing dependencies.
*   A terminal emulator that supports ANSI color codes (for `ColourVideo` mode).

## Getting Started

**1. Clone the repository and navigate to the project directory:**
`bash
cd videoproject
`

**2. Build the project using Maven:**
`bash
mvn clean install
`

**3. Run the application:**
You can execute the main class directly via Maven:
`bash
mvn exec:java -Dexec.mainClass="com.daniel.Main"
`

## Usage
*  If the font size in your terminal is too large or the window is too small it will not display correctly
*  You can add your own videos or text files to the resources folder to use them
