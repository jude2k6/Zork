#!/bin/bash

# Array of folders and their corresponding packages
declare -A folders=(
    ["src/main/java/org/zorkrip/model"]="org.zorkrip.model"
    ["src/main/java/org/zorkrip/engine"]="org.zorkrip.engine"
    ["src/main/java/org/zorkrip/persistence"]="org.zorkrip.persistence"
    ["src/main/java/org/zorkrip/util"]="org.zorkrip.util"
    ["src/main/java/org/zorkrip/ui/fx"]="org.zorkrip.ui.fx"
    ["src/main/java/org/zorkrip/ui/fx/controller"]="org.zorkrip.ui.fx.controller"
    ["src/test/java/org/zorkrip/dev"]="org.zorkrip.dev"
)

echo "Updating package statements..."

for folder in "${!folders[@]}"; do
    package_name=${folders[$folder]}
    # Loop over all .java files in the folder
    find "$folder" -name "*.java" | while read file; do
        # Remove any existing package statement (first line if it starts with 'package')
        sed -i '1{/^package /d}' "$file"
        # Prepend the correct package statement
        sed -i "1i package $package_name;" "$file"
        echo "Updated $file -> $package_name"
    done
done

echo "All package statements updated."
