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

package utils

import base.SpecBase
import models.other.oil.{HaveYouRegisteredForRebatedOils, HaveYouRegisteredForTiedOils}
import models.other.oil.SelectAnOilService.{RebatedOilsEnquiryService, TiedOilsEnquiryService}
import models.wrongcredentials.FindingYourAccount


class NextPageSpec extends SpecBase {

  implicit val urlHelper = new UrlHelper(frontendAppConfig)


  def nextPage[A, B](np: NextPage[A, B], userSelection: B, urlRedirect: String): Unit = {
    s"$userSelection is selected" should {
      s"redirect to $urlRedirect" in {
        val result = np.get(userSelection)
        result.url mustBe urlRedirect
      }
    }
  }

  "SelectAnOilService" when {
    behave like nextPage(NextPage.selectAnOilService, TiedOilsEnquiryService, "/business-account/add-tax/other/oil/tied")
    behave like nextPage(NextPage.selectAnOilService, RebatedOilsEnquiryService, "/business-account/add-tax/other/oil/rebated")
  }

  "HaveYouRegisteredForTiedOils" when {
    behave like nextPage(
      NextPage.haveYouRegisteredForTiedOils,
      HaveYouRegisteredForTiedOils.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMCE-TO/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.haveYouRegisteredForTiedOils,
      HaveYouRegisteredForTiedOils.No,
      "/business-account/add-tax/other/oil/tied/register"
    )
  }


  "HaveYouRegisteredForRebatedOils" when {
    behave like nextPage(
      NextPage.haveYouRegisteredForRebatedOils,
      HaveYouRegisteredForRebatedOils.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMCE-RO/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.haveYouRegisteredForRebatedOils,
      HaveYouRegisteredForRebatedOils.No,
      "/business-account/add-tax/other/oil/rebated/register"
    )
  }

  "FindingYourAccountFormProvider" when {
    def governmentGatewayUrlGenerator(forgottenOption: String): String =
      s"http://localhost:9898/government-gateway-lost-credentials-frontend/" +
        s"choose-your-account?continue=%2Fbusiness-account&origin=business-tax-account&forgottenOption=$forgottenOption"

    behave like nextPage(NextPage.findingYourAccount, FindingYourAccount.DontKnowId, governmentGatewayUrlGenerator("userId"))

    behave like nextPage(NextPage.findingYourAccount, FindingYourAccount.DontKnowPassword,
      governmentGatewayUrlGenerator("password"))

    behave like nextPage(NextPage.findingYourAccount, FindingYourAccount.DontKnowIdOrPassword,
      governmentGatewayUrlGenerator("UserIdAndPassword"))
  }
}
