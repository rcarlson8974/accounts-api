package com.os.accounts.util

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import groovy.transform.CompileStatic

import java.text.SimpleDateFormat

@CompileStatic
class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {

  ObjectMapper() {
    super()
    this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
    this.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
    this.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
    this.writerWithDefaultPrettyPrinter()
  }
}
