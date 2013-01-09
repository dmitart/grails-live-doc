package com.dmitart.livedoc

class Util {
  static String nicefy(String name) {
    if (name.endsWith("Spec")) {
      name = name[0..-5]
    }
    name = name.replaceAll(/_/, ' ')
    return name
  }
}
