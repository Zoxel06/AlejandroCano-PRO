#define kbd_es_es
#include "DigiKeyboard.h"

void setup() {
  DigiKeyboard.update();
  DigiKeyboard.delay(5000);
}

void loop() {
  DigiKeyboard.sendKeyStroke(KEY_R, MOD_GUI_LEFT);
  delay(250);
  DigiKeyboard.println("powershell");
  delay(2500);
  DigiKeyboard.println(F("ps"));
  while(1);
}