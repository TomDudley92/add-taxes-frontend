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
import models.other.importexports.{DoYouHaveEORINumber, DoYouWantToAddImportExport}
import models.OtherTaxes
import models.employer.cis.uk.contractor.{DoesBusinessManagePAYE, IsBusinessRegisteredForPAYE}
import models.employer.pension.WhichPensionSchemeToAdd
import models.other.aeoi.HaveYouRegisteredAEOI
import models.other.alcohol.atwd.AreYouRegisteredWarehousekeeper
import models.other.alcohol.awrs.SelectAlcoholScheme
import models.other.charity.DoYouHaveCharityReference
import models.other.gambling.SelectGamblingOrGamingDuty
import models.other.gambling.gbd.AreYouRegisteredGTS
import models.other.gambling.mgd.DoYouHaveMGDRegistration
import models.other.importexports.dan.DoYouHaveDAN
import models.other.importexports.emcs.DoYouHaveASEEDNumber
import models.other.importexports.nes.DoYouHaveCHIEFRole
import models.other.oil.SelectAnOilService.{RebatedOilsEnquiryService, TiedOilsEnquiryService}
import models.other.oil.{HaveYouRegisteredForRebatedOils, HaveYouRegisteredForTiedOils}
import models.sa.SelectSACategory
import models.sa.trust.HaveYouRegisteredTrust
import models.sa.partnership.{DoYouWantToAddPartner, HaveYouRegisteredPartnership}
import models.vat.moss.uk.{OnlineVATAccount, RegisteredForVATUk}
import models.wrongcredentials.FindingYourAccount
import utils.nextpage.NextPageSpecBase

class NextPageSpec extends NextPageSpecBase {

  "EconomicOperatorsRegistrationAndIdentification" when {
    behave like nextPage(
      NextPage.icsEori,
      DoYouHaveEORINumber.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMRC-ICS-ORG/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.icsEori,
      DoYouHaveEORINumber.No,
      "/business-account/add-tax/other/import-export/ics/register"
    )
  }

  "DoYouHaveSEEDNumber" when {
    behave like nextPage(
      NextPage.doYouHaveASEEDNumber,
      DoYouHaveASEEDNumber.No,
      "/business-account/add-tax/other/import-export/emcs/register"
    )

    behave like nextPage(
      NextPage.doYouHaveASEEDNumber,
      DoYouHaveASEEDNumber.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMRC-EMCS-ORG/request-access-tax-scheme?continue=%2Fbusiness-account"
    )
  }

  "DoYouHaveDAN" when {
    behave like nextPage(
      NextPage.doYouHaveDAN,
      DoYouHaveDAN.No,
      "/business-account/add-tax/other/import-export/ddes/register"
    )

    behave like nextPage(
      NextPage.doYouHaveDAN,
      DoYouHaveDAN.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMCE-DDES/request-access-tax-scheme?continue=%2Fbusiness-account"
    )
  }

  "DoYouHaveCHIEFRole" when {
    behave like nextPage(
      NextPage.doYouHaveCHIEFHasEORIRole,
      DoYouHaveCHIEFRole.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMCE-NES/request-access-tax-scheme?continue=%2Fbusiness-account"
    )
  }

  "nesEori" when {
    behave like nextPage(
      NextPage.nesEori,
      DoYouHaveEORINumber.Yes,
      "/business-account/add-tax/other/import-export/nes/has-eori"
    )

    behave like nextPage(
      NextPage.nesEori,
      DoYouHaveEORINumber.No,
      "/business-account/add-tax/other/import-export/nes/no-eori"
    )
  }

  "DoYouWantToAddImportExport" when {
    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.EMCS,
      "/business-account/add-tax/other/import-export/emcs"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.ICS,
      "/business-account/add-tax/other/import-export/ics"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.DDES,
      "/business-account/add-tax/other/import-export/ddes"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.NOVA,
      "http://localhost:8080/portal/nova/normal?lang=eng"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.NCTS,
      "/business-account/add-tax/other/import-export/ncts"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.eBTI,
      "/business-account/add-tax/other/import-export/ebti"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.NES,
      "/business-account/add-tax/other/import-export/nes"
    )

    behave like nextPage(
      NextPage.doYouWantToAddImportExport,
      DoYouWantToAddImportExport.ISD,
      "http://localhost:8080/hmce/ecom/is2/static/is2.html"
    )
  }

  "WhichPensionSchemeToAdd" when {
    behave like nextPage(
      NextPage.whichPensionSchemeToAdd,
      WhichPensionSchemeToAdd.Administrators,
      "http://localhost:8080/portal/service/pensions-administrators?action=enrol&step=hasid&lang=eng"
    )

    behave like nextPage(
      NextPage.whichPensionSchemeToAdd,
      WhichPensionSchemeToAdd.Practitioners,
      "http://localhost:8080/portal/service/pensions-practitioners?action=enrol&step=hasid&lang=eng"
    )
  }

  "gtsGBD" when {
    behave like nextPage(
      NextPage.gbdGTS,
      AreYouRegisteredGTS.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMRC-GTS-GBD/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.gbdGTS,
      AreYouRegisteredGTS.No,
      "/business-account/add-tax/other/gambling/gbd/register"
    )
  }

  "gtsPBD" when {
    behave like nextPage(
      NextPage.pbdGTS,
      AreYouRegisteredGTS.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMRC-GTS-PBD/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.pbdGTS,
      AreYouRegisteredGTS.No,
      "/business-account/add-tax/other/gambling/pbd/register"
    )
  }

  "gtsRGD" when {
    behave like nextPage(
      NextPage.rgdGTS,
      AreYouRegisteredGTS.Yes,
      "http://localhost:9555/enrolment-management-frontend/HMRC-GTS-RGD/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.rgdGTS,
      AreYouRegisteredGTS.No,
      "/business-account/add-tax/other/gambling/rgd/register"
    )
  }

  "SA Partnership" when {
    behave like nextPage(
      NextPage.doYouWantToAddPartner,
      DoYouWantToAddPartner.Yes,
      "https://assets.publishing.service.gov.uk/government/uploads/system/uploads/attachment_data/file/359508/sa401-static.pdf"
    )

    behave like nextPage(
      NextPage.doYouWantToAddPartner,
      DoYouWantToAddPartner.No,
      "/business-account/add-tax/self-assessment/partnership/other"
    )
  }

  "SA Partnership Other" when {
    behave like nextPage(
      NextPage.haveYouRegisteredPartnership,
      HaveYouRegisteredPartnership.Yes,
      "http://localhost:9555/enrolment-management-frontend/IR-SA-PART-ORG/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.haveYouRegisteredPartnership,
      HaveYouRegisteredPartnership.No,
      "https://assets.publishing.service.gov.uk/government/uploads/system/uploads/attachment_data/file/359500/sa400-static.pdf"
    )
  }

  "SA Trusts" when {
    behave like nextPage(
      NextPage.haveYouRegisteredTrust,
      HaveYouRegisteredTrust.Yes,
      "http://localhost:9555/enrolment-management-frontend/IR-SA-TRUST-ORG/request-access-tax-scheme?continue=%2Fbusiness-account"
    )

    behave like nextPage(
      NextPage.haveYouRegisteredTrust,
      HaveYouRegisteredTrust.No,
      "/business-account/add-tax/self-assessment/trust/not-registered"
    )
  }

  "VAT MOSS UK" when {
    behave like nextPage(
      NextPage.registeredForVATUk,
      RegisteredForVATUk.No,
      "/business-account/add-tax/vat/moss/uk/not-vat-registered"
    )

    behave like nextPage(
      NextPage.registeredForVATUk,
      RegisteredForVATUk.Yes,
      "/business-account/add-tax/vat/moss/uk/vat-registered"
    )
  }

  "VAT MOSS UK online VAT Account" when {
    behave like nextPage(
      NextPage.onlineVATAccount,
      OnlineVATAccount.Yes,
      "/business-account/add-tax/vat/moss/uk/vat-registered/other-account"
    )

    behave like nextPage(
      NextPage.onlineVATAccount,
      OnlineVATAccount.No,
      "/business-account/add-tax/vat/moss/uk/vat-registered/no-other-account"
    )
  }

  "Self Assessment" when {
    behave like nextPage(
      NextPage.selectSACategory,
      SelectSACategory.Sa,
      "http://localhost:8080/portal/business-registration/introduction?lang=eng"
    )

    behave like nextPage(
      NextPage.selectSACategory,
      SelectSACategory.Partnership,
      "/business-account/add-tax/self-assessment/partnership"
    )

    behave like nextPage(
      NextPage.selectSACategory,
      SelectSACategory.Trust,
      "/business-account/add-tax/self-assessment/trust"
    )
  }

}
