# Nom de l'exécutable (ajoute .exe pour Windows)
TARGET = run.exe

# Compilateur
CXX = g++

# --- CHEMIN VERS ALLEGRO (IMPORTANT) ---
ALLEGRO_PATH = C:/allegro

# Flags de compilation
# -I indique où trouver les fichiers .h
CXXFLAGS = -Wall -Wextra -std=c++11 -I$(ALLEGRO_PATH)/include

# Flags de lien (Linker)
# -L indique où trouver les fichiers .a (librairies statiques)
LDFLAGS = -L$(ALLEGRO_PATH)/lib

# Bibliothèques Allegro (version Monolith ou séparée)
# Note : Sous Windows, il faut souvent lier explicitement les dépendances si on n'utilise pas la version "monolith".
# Voici la liste standard pour ce que vous utilisez :
LIBS = -lallegro -lallegro_dialog -lallegro_primitives -lallegro_font -lallegro_ttf

# Fichiers source
SRCS = main.cpp

# Fichiers objets
OBJS = $(SRCS:.cpp=.o)

# Règle par défaut
all: $(TARGET)

# Compilation de l'exécutable
$(TARGET): $(OBJS)
	$(CXX) $(CXXFLAGS) -o $(TARGET) $(OBJS) $(LDFLAGS) $(LIBS)

# Compilation des fichiers objets
%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

# Nettoyage
clean:
	del $(OBJS) $(TARGET)