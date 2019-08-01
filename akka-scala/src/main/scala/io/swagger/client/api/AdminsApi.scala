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
package io.swagger.client.api

import io.swagger.client.model.Breed
import io.swagger.client.model.Dog
import io.swagger.client.core._
import io.swagger.client.core.CollectionFormats._
import io.swagger.client.core.ApiKeyLocations._

object AdminsApi {

  /**
   * Adds a breed
   * 
   * Expected answers:
   *   code 201 : Breed (breed created)
   *   code 400 :  (invalid input, object invalid)
   *   code 409 :  (an existing breed already exists)
   * 
   * @param breed Breed to add
   */
  def addBreed(breed: Option[Breed] = None): ApiRequest[Breed] =
    ApiRequest[Breed](ApiMethods.POST, "https://virtserver.swaggerhub.com/brians/Doghouse/1.0.0", "/breeds", "application/json")
      .withBody(breed)
      .withSuccessResponse[Breed](201)
      .withErrorResponse[Unit](400)
      .withErrorResponse[Unit](409)
        /**
   * Adds a dog
   * 
   * Expected answers:
   *   code 201 : Dog (dog created)
   *   code 400 :  (invalid input, object invalid)
   *   code 409 :  (an existing dog already exists)
   * 
   * @param dog Dog to add
   */
  def addDog(dog: Option[Dog] = None): ApiRequest[Dog] =
    ApiRequest[Dog](ApiMethods.POST, "https://virtserver.swaggerhub.com/brians/Doghouse/1.0.0", "/dogs", "application/json")
      .withBody(dog)
      .withSuccessResponse[Dog](201)
      .withErrorResponse[Unit](400)
      .withErrorResponse[Unit](409)
      

}
