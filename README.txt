IMMERSIVE AUDIO - Forge 1.20.1 (47.x)

1. Descarga el Forge MDK 1.20.1 (47.3.x o la que uses) de files.minecraftforge.net y descomprimelo.
2. Borra su carpeta src/main/java/com/example y su src/main/resources/META-INF/mods.toml.
3. Copia la carpeta "src" de este proyecto encima de la del MDK.
4. En gradle.properties del MDK pon:
     mod_id=immersiveaudio
     mod_name=Immersive Audio
     mod_group_id=com.immersiveaudio
     mod_version=1.0.0
5. Ejecuta:  gradlew build   -> el jar queda en build/libs/

SONIDOS (IDs para /playsound o CustomNPCs):
  immersiveaudio:armorequip  immersiveaudio:explosion  immersiveaudio:explosiondistant
  immersiveaudio:guitar      immersiveaudio:zip
