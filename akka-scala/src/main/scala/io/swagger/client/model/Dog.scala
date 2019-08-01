/**
 * Dogs API
 * This is the dogs API
 *
 * OpenAPI spec version: 1.0.0
 * Contact: bsawert@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.model

import io.swagger.client.core.ApiModel
import org.joda.time.DateTime
import java.util.UUID

case class Dog (
  id: Option[Long] = None,
  name: String,
  description: Option[String] = None,
  gender: Option[Gender] = None,
  breed: Option[Seq[Breed]] = None
) extends ApiModel

