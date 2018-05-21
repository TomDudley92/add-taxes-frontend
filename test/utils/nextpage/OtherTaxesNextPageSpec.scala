/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package utils.nextpage

import models.OtherTaxes
import utils.NextPage

class OtherTaxesNextPageSpec extends NextPageSpecBase {

  "OtherTaxes" when {
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.AlcoholAndTobacco,
      "http://localhost:9020/business-account/add-tax/other/alcohol"
    )

    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.AutomaticExchangeOfInformation,
      "http://localhost:9020/business-account/add-tax/other/aeoi"
    )
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.Charities,
      "http://localhost:9020/business-account/add-tax/other/charities"
    )
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.GamblingAndGaming,
      "http://localhost:9020/business-account/add-tax/other/gambling"
    )
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.HousingAndLand,
      "http://localhost:9020/business-account/add-tax/other/land"
    )
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.ImportsExports,
      "http://localhost:9020/business-account/add-tax/other/import-export"
    )
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.OilAndFuel,
      "/business-account/add-tax/other/oil"
    )
    behave like nextPage(
      NextPage.otherTaxes,
      OtherTaxes.FulfilmentHouseDueDiligenceSchemeIntegration,
      "http://localhost:1118/fhdds"
    )
  }
}
