package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController1 {
    public static void main(String[] args) {
        String content = "\n" +
                "1. Babjuk, M., et al., EAU Guidelines on Non-muscle-invasive Bladder Cancer (T1, T1 and CIS), in EAU \n" +
                "Guidelines, Edn. presented at the 33rd EAU Annual Congress Copenhagen. 2018, EAU Guidelines \n" +
                "Office. \n" +
                "https://uroweb.org/guideline/non-muscle-invasive-bladder-cancer/\n" +
                "2. Witjes, J.A., et al., EAU Guidelines on Muscle-invasive and Metastatic Bladder Cancer in EAU \n" +
                "Guidelines, Edn. presentat at the 33rd EAU Annual Congress Copenhagen. 2018, EAU Guidelines \n" +
                "Office. \n" +
                "https://uroweb.org/guideline/bladder-cancer-muscle-invasive-and-metastatic/\n" +
                "3. Gakis, G., et al., EAU Guidelines on Primary Urethral Carcinoma, in EAU Guidelines, Edn. presented \n" +
                "at the 33rd EAU Annual Congress, Copenhagen. 2018, EAU Guidelines Office. \n" +
                "https://uroweb.org/guideline/primary-urethral-carcinoma/\n" +
                "4. Roupret, M., et al. European Association of Urology Guidelines on Upper Urinary Tract Urothelial \n" +
                "Carcinoma: 2017 Update. Eur Urol, 2017. 68: 868.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28867446\n" +
                "5. Phillips B, et al. Oxford Centre for Evidence-based Medicine Levels of Evidence. Updated by \n" +
                "Jeremy Howick March 2009. 1998.\n" +
                "http://www.cebm.net/oxford-centre-evidence-based-medicine-levels-evidence-march-2009/\n" +
                "6. Guyatt, G.H., et al. What is “quality of evidence” and why is it important to clinicians? BMJ, 2008. \n" +
                "336: 995.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/18456631\n" +
                "7. Guyatt, G.H., et al. GRADE: an emerging consensus on rating quality of evidence and strength of \n" +
                "recommendations. BMJ, 2008. 336: 924.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/18436948\n" +
                "8. Peyronnet, B., et al. Oncological Outcomes of Laparoscopic Nephroureterectomy Versus Open \n" +
                "Radical Nephroureterectomy for Upper Tract Urothelial Carcinoma: An European Association of \n" +
                "Urology Guidelines Systematic Review. Eur Urol Focus, 2017.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/29154042\n" +
                "9. Dominguez-Escrig, J.L., et al. Potential Benefit of Lymph Node Dissection During Radical \n" +
                "Nephroureterectomy for Upper Tract Urothelial Carcinoma: A Systematic Review by the European \n" +
                "Association of Urology Guidelines Panel on Non-muscle-invasive Bladder Cancer. Eur Urol Focus, \n" +
                "2017.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/29158169\n" +
                "10. Siegel, R.L., et al. Cancer statistics, 2017. CA Cancer J Clin, 2017. 66: 7.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26742998\n" +
                "11. Munoz, J.J., et al. Upper tract urothelial neoplasms: incidence and survival during the last 2 \n" +
                "decades. J Urol, 2000. 164: 1523.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/11025695\n" +
                "19UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 2018\n" +
                "12. Soria, F., et al. Epidemiology, diagnosis, preoperative evaluation and prognostic assessment of \n" +
                "upper-tract urothelial carcinoma (UTUC). World J Urol, 2017. 35: 379.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27604375\n" +
                "13. Cosentino, M., et al. Upper urinary tract urothelial cell carcinoma: location as a predictive factor for \n" +
                "concomitant bladder carcinoma. World J Urol, 2013. 31: 141.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22552732\n" +
                "14. Singla, N., et al. A Multi-Institutional Comparison of Clinicopathological Characteristics and \n" +
                "Oncologic Outcomes of Upper Tract Urothelial Carcinoma in China and the United States. J Urol, \n" +
                "2017. 197: 1208.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27887951\n" +
                "15. Xylinas, E., et al. Multifocal Carcinoma In Situ of the Upper Tract Is Associated With High Risk of \n" +
                "Bladder Cancer Recurrence. Eur Urol. 61: 1069.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22402109\n" +
                "16. Li, W.M., et al. Oncologic outcomes following three different approaches to the distal ureter and \n" +
                "bladder cuff in nephroureterectomy for primary upper urinary tract urothelial carcinoma. Eur Urol, \n" +
                "2010. 57: 963.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20079965\n" +
                "17. Margulis, V., et al. Outcomes of radical nephroureterectomy: a series from the Upper Tract Urothelial \n" +
                "Carcinoma Collaboration. Cancer, 2009. 115: 1224.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19156917\n" +
                "18. Shariat, S.F., et al. Gender differences in radical nephroureterectomy for upper tract urothelial \n" +
                "carcinoma. World J Urol, 2011. 29: 481.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20886219\n" +
                "19. Roupret, M., et al. Upper urinary tract urothelial cell carcinomas and other urological malignancies \n" +
                "involved in the hereditary nonpolyposis colorectal cancer (lynch syndrome) tumor spectrum. Eur \n" +
                "Urol, 2008. 54: 1226.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/18715695\n" +
                "20. Audenet, F., et al. A proportion of hereditary upper urinary tract urothelial carcinomas are \n" +
                "misclassified as sporadic according to a multi-institutional database analysis: proposal of patient-\n" +
                "specific risk identification tool. BJU Int, 2012. 110: E583.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22703159\n" +
                "21. Acher, P., et al. Towards a rational strategy for the surveillance of patients with Lynch syndrome \n" +
                "(hereditary non-polyposis colon cancer) for upper tract transitional cell carcinoma. BJU Int, 2010. \n" +
                "106: 300.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20553255\n" +
                "22. Colin, P., et al. Environmental factors involved in carcinogenesis of urothelial cell carcinomas of the \n" +
                "upper urinary tract. BJU Int, 2009. 104: 1436.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19689473\n" +
                "23. Crivelli, J.J., et al. Effect of smoking on outcomes of urothelial carcinoma: a systematic review of the \n" +
                "literature. Eur Urol, 2014. 65: 742.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23810104\n" +
                "24. Grollman, A.P., et al. Aristolochic acid and the etiology of endemic (Balkan) nephropathy. Proc Natl \n" +
                "Acad Sci U S A, 2007. 104: 12129.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/17620607\n" +
                "25. Chiou, H.Y., et al. Incidence of transitional cell carcinoma and arsenic in drinking water: a follow-up \n" +
                "study of 8,102 residents in an arseniasis-endemic area in northeastern Taiwan. Am J Epidemiol, \n" +
                "2001. 153: 411.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/11226969\n" +
                "26. Chen, C.H., et al. Aristolochic acid-associated urothelial cancer in Taiwan. Proc Natl Acad Sci U S A, \n" +
                "2012. 109: 8241.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22493262\n" +
                "27. Roupret, M., et al. Genetic variability in 8q24 confers susceptibility to urothelial carcinoma of the \n" +
                "upper urinary tract and is linked with patterns of disease aggressiveness at diagnosis. J Urol, 2012. \n" +
                "187: 424.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22177160\n" +
                "28. Sakano, S., et al. Impact of variant histology on disease aggressiveness and outcome after \n" +
                "nephroureterectomy in Japanese patients with upper tract urothelial carcinoma. Int J Clin Oncol, \n" +
                "2015. 20: 362.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24964974\n" +
                "UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 201820\n" +
                "29. Ouzzane, A., et al. Small cell carcinoma of the upper urinary tract (UUT-SCC): report of a rare entity \n" +
                "and systematic review of the literature. Cancer Treat Rev, 2011. 37: 366.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21257269\n" +
                "30. Rink, M., et al. Impact of histological variants on clinical outcomes of patients with upper urinary \n" +
                "tract urothelial carcinoma. J Urol, 2012. 188: 398.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22698626\n" +
                "31. Masson-Lecomte, A., et al. Impact of micropapillary histological variant on survival after radical \n" +
                "nephroureterectomy for upper tract urothelial carcinoma. World J Urol, 2014. 32: 531.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23907662\n" +
                "32. Kim, J.K., et al. Variant histology as a significant predictor of survival after radical \n" +
                "nephroureterectomy in patients with upper urinary tract urothelial carcinoma. Urol Oncol, 2017. 35: \n" +
                "458 e9.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28347659\n" +
                "33. Olgac, S., et al. Urothelial carcinoma of the renal pelvis: a clinicopathologic study of 130 cases. Am \n" +
                "J Surg Pathol, 2004. 28: 1545.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/15577672\n" +
                "34. Perez-Montiel, D., et al. High-grade urothelial carcinoma of the renal pelvis: clinicopathologic study \n" +
                "of 108 cases with emphasis on unusual morphologic variants. Mod Pathol, 2006. 19: 494.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/16474378\n" +
                "35. Albadine, R., et al. PAX8 (+)/p63 (-) immunostaining pattern in renal collecting duct carcinoma \n" +
                "(CDC): a useful immunoprofile in the differential diagnosis of CDC versus urothelial carcinoma of \n" +
                "upper urinary tract. Am J Surg Pathol, 2010. 34: 965.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/\n" +
                "36. Soukup, V., et al. Prognostic Performance and Reproducibility of the 1973 and 2004/2016 World \n" +
                "Health Organization Grading Classification Systems in Non-muscle-invasive Bladder Cancer: A \n" +
                "European Association of Urology Non-muscle Invasive Bladder Cancer Guidelines Panel Systematic \n" +
                "Review. Eur Urol, 2017. 72: 801.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28457661\n" +
                "37. Brierley, J.D., et al., TNM Classification of Malignant Tumours. 8th ed. 2016.\n" +
                "https://www.uicc.org/8th-edition-uicc-tnm-classification-malignant-tumors-published\n" +
                "38. Roscigno, M., et al. International validation of the prognostic value of subclassification for AJCC \n" +
                "stage pT3 upper tract urothelial carcinoma of the renal pelvis. BJU Int, 2012. 110: 674.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22348322\n" +
                "39. Park, J., et al. Reassessment of prognostic heterogeneity of pT3 renal pelvic urothelial carcinoma: \n" +
                "analysis in terms of proposed pT3 subclassification systems. J Urol, 2014. 192: 1064.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24735938\n" +
                "40. Eble J.N., et al. Tumours of the Urinary System, in World Health Organisation Classification of \n" +
                "Tumors: Pathology and Genetics of Tumours of the Urinary System and Male Genital Organs, S.G. \n" +
                "Eble J.N., Epstein J.I., Sesterhenn I.A., Editor. 2004, IARC Press: Lyon, France.\n" +
                "41. Moch, H., et al., WHO Classification of Tumours of the Urinary System and Male Genital Organs. 4th \n" +
                "ed. ed, ed. O. H. 2016, Lyon, France.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26935559\n" +
                "42. Inman, B.A., et al. Carcinoma of the upper urinary tract: predictors of survival and competing causes \n" +
                "of mortality. Cancer, 2009. 115: 2853.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19434668\n" +
                "43. Cowan, N.C. CT urography for hematuria. Nat Rev Urol, 2012. 9: 218.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22410682\n" +
                "44. Raman, J.D., et al. Does preoperative symptom classification impact prognosis in patients with \n" +
                "clinically localized upper-tract urothelial carcinoma managed by radical nephroureterectomy? Urol \n" +
                "Oncol, 2011. 29: 716.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20056458\n" +
                "45. Ito, Y., et al. Preoperative hydronephrosis grade independently predicts worse pathological \n" +
                "outcomes in patients undergoing nephroureterectomy for upper tract urothelial carcinoma. J Urol, \n" +
                "2011. 185: 1621.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21419429\n" +
                "46. Cowan, N.C., et al. Multidetector computed tomography urography for diagnosing upper urinary \n" +
                "tract urothelial tumour. BJU Int, 2007. 99: 1363.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/17428251\n" +
                "21UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 2018\n" +
                "47. Messer, J.C., et al. Multi-institutional validation of the ability of preoperative hydronephrosis to \n" +
                "predict advanced pathologic tumor stage in upper-tract urothelial carcinoma. Urol Oncol, 2013. 31: \n" +
                "904.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21906967\n" +
                "48. Hurel, S., et al. Influence of preoperative factors on the oncologic outcome for upper urinary tract \n" +
                "urothelial carcinoma after radical nephroureterectomy. World J Urol, 2015. 33: 335.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24810657\n" +
                "49. Verhoest, G., et al. Predictive factors of recurrence and survival of upper tract urothelial carcinomas. \n" +
                "World J Urol, 2011. 29: 495.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21681525\n" +
                "50. Takahashi, N., et al. Gadolinium enhanced magnetic resonance urography for upper urinary tract \n" +
                "malignancy. J Urol, 2010. 183: 1330.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20171676\n" +
                "51. Witjes, J.A., et al. Hexaminolevulinate-guided fluorescence cystoscopy in the diagnosis and \n" +
                "follow-up of patients with non-muscle-invasive bladder cancer: review of the evidence and \n" +
                "recommendations. Eur Urol, 2010. 57: 607.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20116164\n" +
                "52. Rosenthal D.L., et al. The Paris System for Reporting Urinary Cytology. 2016, Springer International \n" +
                "Publishing, Switzerland. \n" +
                "http://www.springer.com/gp/book/9783319228631\n" +
                "53. Messer, J., et al. Urinary cytology has a poor performance for predicting invasive or high-grade \n" +
                "upper-tract urothelial carcinoma. BJU Int, 2011. 108: 701.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21320275\n" +
                "54. Wang, L.J., et al. Diagnostic accuracy of transitional cell carcinoma on multidetector computerized \n" +
                "tomography urography in patients with gross hematuria. J Urol, 2009. 181: 524.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19100576\n" +
                "55. Lee, K.S., et al. MR urography versus retrograde pyelography/ureteroscopy for the exclusion of \n" +
                "upper urinary tract malignancy. Clin Radiol, 2010. 65: 185.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20152273\n" +
                "56. Chen, A.A., et al. Is there a role for FISH in the management and surveillance of patients with upper \n" +
                "tract transitional-cell carcinoma? J Endourol, 2008. 22: 1371.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/18578665\n" +
                "57. Johannes, J.R., et al. Voided urine fluorescence in situ hybridization testing for upper tract urothelial \n" +
                "carcinoma surveillance. J Urol, 2010. 184: 879.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20643443\n" +
                "58. Rojas, C.P., et al. Low biopsy volume in ureteroscopy does not affect tumor biopsy grading in upper \n" +
                "tract urothelial carcinoma. Urol Oncol, 2013. 31: 1696.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22819696\n" +
                "59. Smith, A.K., et al. Inadequacy of biopsy for diagnosis of upper tract urothelial carcinoma: \n" +
                "implications for conservative management. Urology, 2011. 78: 82.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21550642\n" +
                "60. Ishikawa, S., et al. Impact of diagnostic ureteroscopy on intravesical recurrence and survival in \n" +
                "patients with urothelial carcinoma of the upper urinary tract. J Urol, 2010. 184: 883.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20643446\n" +
                "61. Clements, T., et al. High-grade ureteroscopic biopsy is associated with advanced pathology of \n" +
                "upper-tract urothelial carcinoma tumors at definitive surgical resection. J Endourol, 2012. 26: 398.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22192113\n" +
                "62. Brien, J.C., et al. Preoperative hydronephrosis, ureteroscopic biopsy grade and urinary cytology can \n" +
                "improve prediction of advanced upper tract urothelial carcinoma. J Urol, 2010. 184: 69.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20478585\n" +
                "63. Marchioni, M., et al. Impact of diagnostic ureteroscopy on intravesical recurrence in patients \n" +
                "undergoing radical nephroureterectomy for upper tract urothelial cancer: a systematic review and \n" +
                "meta-analysis. BJU Int, 2017. 120: 313.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28621055\n" +
                "64. Yoo, S., et al. Risk of Intravesical Recurrence After Ureteroscopic Biopsy for Upper Tract Urothelial \n" +
                "Carcinoma: Does the Location Matter? J Endourol, 2017. 31: 259.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27785917\n" +
                "65. Bus, M.T., et al. Optical diagnostics for upper urinary tract urothelial cancer: technology, thresholds, \n" +
                "and clinical applications. J Endourol, 2015. 29: 113.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/25178057\n" +
                "UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 201822\n" +
                "66. Abouassaly, R., et al. Troubling outcomes from population-level analysis of surgery for upper tract \n" +
                "urothelial carcinoma. Urology, 2010. 76: 895.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20646743\n" +
                "67. Kata, S.G., et al. Photodynamic diagnostic ureterorenoscopy: A valuable tool in the detection of \n" +
                "upper urinary tract tumour. Photodiagnosis Photodyn Ther, 2016. 13: 255.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26256824\n" +
                "68. Breda, A., et al. Correlation Between Confocal Laser Endomicroscopy (Cellvizio®) and Histological \n" +
                "Grading of Upper Tract Urothelial Carcinoma: A Step Forward for a Better Selection of Patients \n" +
                "Suitable for Conservative Management. Eur Urol Focus, 2017.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28753800\n" +
                "69. Bus, M.T., et al. Optical Coherence Tomography as a Tool for In Vivo Staging and Grading of Upper \n" +
                "Urinary Tract Urothelial Carcinoma: A Study of Diagnostic Accuracy. J Urol, 2016. 196: 1749.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27475968\n" +
                "70. Jeldres, C., et al. A population-based assessment of perioperative mortality after nephroureterectomy \n" +
                "for upper-tract urothelial carcinoma. Urology, 2010. 75: 315.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19963237\n" +
                "71. Lughezzani, G., et al. Prognostic factors in upper urinary tract urothelial carcinomas: a comprehensive \n" +
                "review of the current literature. Eur Urol, 2012. 62: 100.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22381168\n" +
                "72. Kim, H.S., et al. Association between demographic factors and prognosis in urothelial carcinoma of \n" +
                "the upper urinary tract: a systematic review and meta-analysis. Oncotarget, 2017. 8: 7464.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27448978\n" +
                "73. Shariat, S.F., et al. Advanced patient age is associated with inferior cancer-specific survival after \n" +
                "radical nephroureterectomy. BJU Int, 2010. 105: 1672.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19912201\n" +
                "74. Chromecki, T.F., et al. Chronological age is not an independent predictor of clinical outcomes after \n" +
                "radical nephroureterectomy. World J Urol, 2011. 29: 473.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21499902\n" +
                "75. Fernandez, M.I., et al. Evidence-based sex-related outcomes after radical nephroureterectomy for \n" +
                "upper tract urothelial carcinoma: results of large multicenter study. Urology, 2009. 73: 142.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/18845322\n" +
                "76. Matsumoto, K., et al. Racial differences in the outcome of patients with urothelial carcinoma of the \n" +
                "upper urinary tract: an international study. BJU Int, 2011. 108: E304.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21507184\n" +
                "77. Simsir, A., et al. Prognostic factors for upper urinary tract urothelial carcinomas: stage, grade, and \n" +
                "smoking status. Int Urol Nephrol, 2011. 43: 1039.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21547471\n" +
                "78. Rink, M., et al. Impact of smoking on oncologic outcomes of upper tract urothelial carcinoma after \n" +
                "radical nephroureterectomy. Eur Urol, 2013. 63: 1082.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22743166\n" +
                "79. Xylinas, E., et al. Impact of smoking status and cumulative exposure on intravesical recurrence of \n" +
                "upper tract urothelial carcinoma after radical nephroureterectomy. BJU Int, 2014. 114: 56.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24053463\n" +
                "80. Yafi, F.A., et al. Impact of tumour location versus multifocality in patients with upper tract urothelial \n" +
                "carcinoma treated with nephroureterectomy and bladder cuff excision: a homogeneous series \n" +
                "without perioperative chemotherapy. BJU Int, 2012. 110: E7.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22177329\n" +
                "81. Ouzzane, A., et al. Ureteral and multifocal tumours have worse prognosis than renal pelvic tumours \n" +
                "in urothelial carcinoma of the upper urinary tract treated by nephroureterectomy. Eur Urol, 2011. 60: \n" +
                "1258.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21665356\n" +
                "82. Chromecki, T.F., et al. The impact of tumor multifocality on outcomes in patients treated with radical \n" +
                "nephroureterectomy. Eur Urol, 2012. 61: 245.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21975249\n" +
                "83. Williams, A.K., et al. Multifocality rather than tumor location is a prognostic factor in upper tract \n" +
                "urothelial carcinoma. Urol Oncol, 2013. 31: 1161.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23415596\n" +
                "84. Sundi, D., et al. Upper tract urothelial carcinoma: impact of time to surgery. Urol Oncol, 2012. 30: 266.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20869888\n" +
                "23UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 2018\n" +
                "85. Gadzinski, A.J., et al. Long-term outcomes of immediate versus delayed nephroureterectomy for \n" +
                "upper tract urothelial carcinoma. J Endourol, 2012. 26: 566.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21879886\n" +
                "86. Lee, J.N., et al. Impact of surgical wait time on oncologic outcomes in upper urinary tract urothelial \n" +
                "carcinoma. J Surg Oncol, 2014. 110: 468.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/25059848\n" +
                "87. Waldert, M., et al. A delay in radical nephroureterectomy can lead to upstaging. BJU Int, 2010. 105: \n" +
                "812.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19732052\n" +
                "88. Berod, A.A., et al. The role of American Society of Anesthesiologists scores in predicting urothelial \n" +
                "carcinoma of the upper urinary tract outcome after radical nephroureterectomy: results from a \n" +
                "national multi-institutional collaborative study. BJU Int, 2012. 110: E1035.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22568669\n" +
                "89. Carrion, A., et al. Intraoperative prognostic factors and atypical patterns of recurrence in patients \n" +
                "with upper urinary tract urothelial carcinoma treated with laparoscopic radical nephroureterectomy. \n" +
                "Scand J Urol, 2016. 50: 305.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26926709\n" +
                "90. Ehdaie, B., et al. Obesity adversely impacts disease specific outcomes in patients with upper tract \n" +
                "urothelial carcinoma. J Urol, 2011. 186: 66.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21571333\n" +
                "91. Dalpiaz, O., et al. Validation of the pretreatment derived neutrophil-lymphocyte ratio as a prognostic \n" +
                "factor in a European cohort of patients with upper tract urothelial carcinoma. Br J Cancer, 2014. \n" +
                "110: 2531.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24691424\n" +
                "92. Mbeutcha, A., et al. Prognostic factors and predictive tools for upper tract urothelial carcinoma: a \n" +
                "systematic review. World J Urol, 2017. 35: 337.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27101100\n" +
                "93. Petrelli, F., et al. Prognostic Factors of Overall Survival in Upper Urinary Tract Carcinoma: A \n" +
                "Systematic Review and Meta-analysis. Urology, 2017. 100: 9.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27516121\n" +
                "94. Fajkovic, H., et al. Prognostic value of extranodal extension and other lymph node parameters in \n" +
                "patients with upper tract urothelial carcinoma. J Urol, 2 012. 187: 845.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22248522\n" +
                "95. Roscigno, M., et al. Lymphadenectomy at the time of nephroureterectomy for upper tract urothelial \n" +
                "cancer. Eur Urol, 2011. 60: 776.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21798659\n" +
                "96. Lughezzani, G., et al. A critical appraisal of the value of lymph node dissection at nephroureterectomy \n" +
                "for upper tract urothelial carcinoma. Urology, 2010. 75: 118.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19864000\n" +
                "97. Kikuchi, E., et al. Lymphovascular invasion predicts clinical outcomes in patients with node-negative \n" +
                "upper tract urothelial carcinoma. J Clin Oncol, 2009. 27: 612.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19075275\n" +
                "98. Novara, G., et al. Prognostic role of lymphovascular invasion in patients with urothelial carcinoma of \n" +
                "the upper urinary tract: an international validation study. Eur Urol, 2010. 57: 1064.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20071073\n" +
                "99. Godfrey, M.S., et al. Prognostic indicators for upper tract urothelial carcinoma after radical \n" +
                "nephroureterectomy: the impact of lymphovascular invasion. BJU Int, 2012. 110: 798.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22313599\n" +
                "100. Colin, P., et al. Influence of positive surgical margin status after radical nephroureterectomy on upper \n" +
                "urinary tract urothelial carcinoma survival. Ann Surg Oncol, 2012. 19: 3613.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22843187\n" +
                "101. Zigeuner, R., et al. Tumour necrosis is an indicator of aggressive biology in patients with urothelial \n" +
                "carcinoma of the upper urinary tract. Eur Urol, 2010. 57: 575.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19959276\n" +
                "102. Seitz, C., et al. Association of tumor necrosis with pathological features and clinical outcome \n" +
                "in 754 patients undergoing radical nephroureterectomy for upper tract urothelial carcinoma: an \n" +
                "international validation study. J Urol, 2010. 184: 1895.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20846680\n" +
                "UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 201824\n" +
                "103. Remzi, M., et al. Tumour architecture is an independent predictor of outcomes after \n" +
                " nephroureterectomy: a multi-institutional analysis of 1363 patients. BJU Int, 2009. 103: 307.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/18990163\n" +
                "104. Fritsche, H.M., et al. Macroscopic sessile tumor architecture is a pathologic feature of biologically \n" +
                "aggressive upper tract urothelial carcinoma. Urol Oncol, 2012. 30: 666.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20933445\n" +
                "105. Wheat, J.C., et al. Concomitant carcinoma in situ is a feature of aggressive disease in patients with \n" +
                "organ confined urothelial carcinoma following radical nephroureterectomy. Urol Oncol, 2012. 30: 252.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20451416\n" +
                "106. Redrow, G.P., et al. Upper Urinary Tract Carcinoma In Situ: Current Knowledge, Future Direction. \n" +
                "J Urol, 2017. 197: 287.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27664578\n" +
                "107. Favaretto, R.L., et al. Prognostic role of decreased E-cadherin expression in patients with upper \n" +
                "tract urothelial carcinoma: a multi-institutional study. World J Urol, 2017. 35: 113.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21631698\n" +
                "108. Soria, F., et al. HER2 overexpression is associated with worse outcomes in patients with upper tract \n" +
                "urothelial carcinoma (UTUC). World J Urol, 2017. 35: 251.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27272502\n" +
                "109. Scarpini, S., et al. Impact of the expression of Aurora-A, p53, and MIB-1 on the prognosis of \n" +
                "urothelial carcinomas of the upper urinary tract. Urol Oncol, 2012. 30: 182.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20189840\n" +
                "110. Roupret, M., et al. Microsatellite instability as predictor of survival in patients with invasive upper \n" +
                "urinary tract transitional cell carcinoma. Urology, 2005. 65: 1233.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/15922421\n" +
                "111. Krabbe, L.M., et al. Prognostic Value of PD-1 and PD-L1 Expression in Patients with High Grade \n" +
                "Upper Tract Urothelial Carcinoma. J Urol, 2017. 198: 1253.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28668287\n" +
                "112. Margulis, V., et al. Preoperative multivariable prognostic model for prediction of nonorgan confined \n" +
                "urothelial carcinoma of the upper urinary tract. J Urol, 2010. 184: 453.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20620397\n" +
                "113. Favaretto, R.L., et al. Combining imaging and ureteroscopy variables in a preoperative multivariable \n" +
                "model for prediction of muscle-invasive and non-organ confined disease in patients with upper tract \n" +
                "urothelial carcinoma. BJU Int, 2012. 109: 77.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21631698\n" +
                "114. Cha, E.K., et al. Predicting clinical outcomes after radical nephroureterectomy for upper tract \n" +
                "urothelial carcinoma. Eur Urol, 2012. 61: 818.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22284969\n" +
                "115. Yates, D.R., et al. Cancer-specific survival after radical nephroureterectomy for upper urinary tract \n" +
                "urothelial carcinoma: proposal and multi-institutional validation of a post-operative nomogram. Br J \n" +
                "Cancer, 2012. 106: 1083.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22374463\n" +
                "116. Seisen, T., et al. Postoperative nomogram to predict cancer-specific survival after radical \n" +
                "nephroureterectomy in patients with localised and/or locally advanced upper tract urothelial \n" +
                "carcinoma without metastasis. BJU Int, 2014. 114: 733.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24447471\n" +
                "117. Roupret, M., et al. Prediction of cancer specific survival after radical nephroureterectomy for upper \n" +
                "tract urothelial carcinoma: development of an optimized postoperative nomogram using decision \n" +
                "curve analysis. J Urol, 2013. 189: 1662.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23103802\n" +
                "118. Ku, J.H., et al. External validation of an online nomogram in patients undergoing radical \n" +
                "nephroureterectomy for upper urinary tract urothelial carcinoma. Br J Cancer, 2013. 109: 1130.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23949152\n" +
                "119. Krabbe, L.M., et al. Postoperative Nomogram for Relapse-Free Survival in Patients with High Grade \n" +
                "Upper Tract Urothelial Carcinoma. J Urol, 2017. 197: 580.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27670916\n" +
                "120. Seisen, T., et al. A Systematic Review and Meta-analysis of Clinicopathologic Factors Linked \n" +
                "to Intravesical Recurrence After Radical Nephroureterectomy to Treat Upper Tract Urothelial \n" +
                "Carcinoma. Eur Urol, 2015. 67: 1122.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/25488681\n" +
                "25UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 2018\n" +
                "121. Roupret, M., et al. A new proposal to risk stratify urothelial carcinomas of the upper urinary tract \n" +
                "(UTUCs) in a predefinitive treatment setting: low-risk versus high-risk UTUCs. Eur Urol, 2014. 66: 181.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24361259\n" +
                "122. Seisen, T., et al. Risk-adapted strategy for the kidney-sparing management of upper tract tumours. \n" +
                "Nat Rev Urol, 2015. 12: 155.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/25708579\n" +
                "123. Seisen, T., et al. Oncologic Outcomes of Kidney-sparing Surgery Versus Radical Nephroureterectomy \n" +
                "for Upper Tract Urothelial Carcinoma: A Systematic Review by the EAU Non-muscle Invasive \n" +
                "Bladder Cancer Guidelines Panel. European Urology. 70: 1052.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27477528\n" +
                "124. Cutress, M.L., et al. Long-term endoscopic management of upper tract urothelial carcinoma: \n" +
                "20-year single-centre experience. BJU Int, 2012. 110: 1608.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22564677\n" +
                "125. Cutress, M.L., et al. Ureteroscopic and percutaneous management of upper tract urothelial \n" +
                "carcinoma (UTUC): systematic review. BJU Int, 2012. 110: 614.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22471401\n" +
                "126. Cornu, J.N., et al. Oncologic control obtained after exclusive flexible ureteroscopic management of \n" +
                "upper urinary tract urothelial cell carcinoma. World J Urol, 2010. 28: 151.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20044752\n" +
                "127. Villa, L., et al. Early repeated ureteroscopy within 6-8 weeks after a primary endoscopic treatment in \n" +
                "patients with upper tract urothelial cell carcinoma: preliminary findings. World J Urol, 2016. 34: 1201.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26699629\n" +
                "128. Vemana, G., et al. Survival Comparison Between Endoscopic and Surgical Management for Patients \n" +
                "With Upper Tract Urothelial Cancer: A Matched Propensity Score Analysis Using Surveillance, \n" +
                "Epidemiology and End Results-Medicare Data. Urology, 2016. 95: 115.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27233931\n" +
                "129. Roupret, M., et al. Upper urinary tract transitional cell carcinoma: recurrence rate after percutaneous \n" +
                "endoscopic resection. Eur Urol, 2007. 51: 709.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/16911852\n" +
                "130. Jeldres, C., et al. Segmental ureterectomy can safely be performed in patients with transitional cell \n" +
                "carcinoma of the ureter. J Urol, 2010. 183: 1324.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20171666\n" +
                "131. Colin, P., et al. Comparison of oncological outcomes after segmental ureterectomy or radical \n" +
                "nephroureterectomy in urothelial carcinomas of the upper urinary tract: results from a large French \n" +
                "multicentre study. BJU Int, 2012. 110: 1134.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22394612\n" +
                "132. Lughezzani, G., et al. Nephroureterectomy and segmental ureterectomy in the treatment of invasive \n" +
                "upper tract urothelial carcinoma: A population-based study of 2299 patients. Eur J Cancer, 2009. \n" +
                "45: 3291.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19864000\n" +
                "133. Giannarini, G., et al. Antegrade perfusion with bacillus Calmette-Guerin in patients with non-muscle-\n" +
                "invasive urothelial carcinoma of the upper urinary tract: who may benefit? Eur Urol, 2011. 60: 955.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21807456\n" +
                "134. Irie, A., et al. Intravesical instillation of bacille Calmette-Guerin for carcinoma in situ of the urothelium \n" +
                "involving the upper urinary tract using vesicoureteral reflux created by a double-pigtail catheter. \n" +
                "Urology, 2002. 59: 53.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/11796281\n" +
                "135. Phe, V., et al. Does the surgical technique for management of the distal ureter influence the outcome \n" +
                "after nephroureterectomy? BJU Int, 2011. 108: 130.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/11796281\n" +
                "136. Xylinas, E., et al. Impact of Distal Ureter Management on Oncologic Outcomes Following Radical \n" +
                "Nephroureterectomy for Upper Tract Urothelial Carcinoma. European Urology. 65: 210.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22579047\n" +
                "137. Xylinas, E., et al. Prediction of Intravesical Recurrence After Radical Nephroureterectomy: \n" +
                "Development of a Clinical Decision-making Tool. European Urology. 65: 650.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24070577\n" +
                "138. Roupret, M., et al. Oncological risk of laparoscopic surgery in urothelial carcinomas. World J Urol, \n" +
                "2009. 27: 81.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19020880\n" +
                "UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 201826\n" +
                "139. Ong, A.M., et al. Trocar site recurrence after laparoscopic nephroureterectomy. J Urol, 2003. 170: \n" +
                "1301.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/14501747\n" +
                "140. Favaretto, R.L., et al. Comparison between laparoscopic and open radical nephroureterectomy in \n" +
                "a contemporary group of patients: are recurrence and disease-specific survival associated with \n" +
                "surgical technique? Eur Urol, 2010. 58: 645.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20724065\n" +
                "141. Walton, T.J., et al. Oncological outcomes after laparoscopic and open radical nephroureterectomy: \n" +
                "results from an international cohort. BJU Int, 2011. 108: 406.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21078048\n" +
                "142. Ni, S., et al. Laparoscopic versus open nephroureterectomy for the treatment of upper urinary tract \n" +
                "urothelial carcinoma: a systematic review and cumulative analysis of comparative studies. Eur Urol, \n" +
                "2012. 61: 1142.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22349569\n" +
                "143. Ariane, M.M., et al. Assessment of oncologic control obtained after open versus laparoscopic \n" +
                "nephroureterectomy for upper urinary tract urothelial carcinomas (UUT-UCs): results from a large \n" +
                "French multicenter collaborative study. Ann Surg Oncol, 2012. 19: 301.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21691878\n" +
                "144. Simone, G., et al. Laparoscopic versus open nephroureterectomy: perioperative and oncologic \n" +
                "outcomes from a randomised prospective study. Eur Urol, 2009. 56: 520.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/19560259\n" +
                "145. Adibi, M., et al. Oncological outcomes after radical nephroureterectomy for upper tract urothelial \n" +
                "carcinoma: comparison over the three decades. Int J Urol, 2012. 19: 1060.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/22882743\n" +
                "146. Rodriguez, J.F., et al. Utilization and Outcomes of Nephroureterectomy for Upper Tract Urothelial \n" +
                "Carcinoma by Surgical Approach. J Endourol, 2017. 31: 661.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28537436\n" +
                "147. Aboumohamed, A.A., et al. Oncologic Outcomes Following Robot-Assisted Laparoscopic \n" +
                "Nephroureterectomy with Bladder Cuff Excision for Upper Tract Urothelial Carcinoma. J Urol, 2015. \n" +
                "194: 1561.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26192256\n" +
                "148. Kondo, T., et al. Template-based lymphadenectomy in urothelial carcinoma of the upper urinary \n" +
                "tract: impact on patient survival. Int J Urol, 2010. 17: 848.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20812922\n" +
                "149. Moschini, M., et al. Trends of lymphadenectomy in upper tract urothelial carcinoma (UTUC) patients \n" +
                "treated with radical nephroureterectomy. World J Urol, 2017.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28247066\n" +
                "150. Zareba, P., et al. Association between lymph node yield and survival among patients undergoing \n" +
                "radical nephroureterectomy for urothelial carcinoma of the upper tract. Cancer, 2017. 123: 1741.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28152158\n" +
                "151. Fang, D., et al. Prophylactic intravesical chemotherapy to prevent bladder tumors after \n" +
                "nephroureterectomy for primary upper urinary tract urothelial carcinomas: a systematic review and \n" +
                "meta-analysis. Urol Int, 2013. 91: 291.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23948770\n" +
                "152. O’Brien, T., et al. Prevention of bladder tumours after nephroureterectomy for primary upper \n" +
                "urinary tract urothelial carcinoma: a prospective, multicentre, randomised clinical trial of a single \n" +
                "postoperative intravesical dose of mitomycin C (the ODMIT-C Trial). Eur Urol, 2011. 60: 703.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/21684068\n" +
                "153. Ito, A., et al. Prospective randomized phase II trial of a single early intravesical instillation of \n" +
                "pirarubicin (THP) in the prevention of bladder recurrence after nephroureterectomy for upper urinary \n" +
                "tract urothelial carcinoma: the THP Monotherapy Study Group Trial. J Clin Oncol, 2013. 31: 1422.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/23460707\n" +
                "154. Audenet, F., et al. The role of chemotherapy in the treatment of urothelial cell carcinoma of the upper \n" +
                "urinary tract (UUT-UCC). Urol Oncol, 2013. 31: 407.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20884249\n" +
                "155. Kaag, M.G., et al. Changes in renal function following nephroureterectomy may affect the use of \n" +
                "perioperative chemotherapy. Eur Urol, 2010. 58: 581.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20619530\n" +
                "27UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 2018\n" +
                "156. Lane, B.R., et al. Chronic kidney disease after nephroureterectomy for upper tract urothelial \n" +
                "carcinoma and implications for the administration of perioperative chemotherapy. Cancer, 2010. \n" +
                "116: 2967.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20564402\n" +
                "157. Matin, S.F., et al. Incidence of downstaging and complete remission after neoadjuvant chemotherapy \n" +
                "for high-risk upper tract transitional cell carcinoma. Cancer, 2010. 116: 3127.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20564621\n" +
                "158. Seisen, T., et al. Efficacy of Systemic Chemotherapy Plus Radical Nephroureterectomy for \n" +
                "Metastatic Upper Tract Urothelial Carcinoma. Eur Urol. 71: 714.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27912971\n" +
                "159. Sharma, P., et al. Nivolumab in metastatic urothelial carcinoma after platinum therapy (CheckMate \n" +
                "275): a multicentre, single-arm, phase 2 trial. Lancet Oncol, 2017. 18: 312.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28131785\n" +
                "160. Rosenberg, J.E., et al. Atezolizumab in patients with locally advanced and metastatic urothelial \n" +
                "carcinoma who have progressed following treatment with platinum-based chemotherapy: a single-\n" +
                "arm, multicentre, phase 2 trial. Lancet, 2016. 387: 1909.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/26952546\n" +
                "161. Leow, J.J., et al. A Systematic Review and Meta-analysis of Adjuvant and Neoadjuvant Chemotherapy \n" +
                "for Upper Tract Urothelial Carcinoma. Eur Urol, 2014. 66: 529.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24680361\n" +
                "162. Seisen, T., et al. Effectiveness of Adjuvant Chemotherapy After Radical Nephroureterectomy for \n" +
                "Locally Advanced and/or Positive Regional Lymph Node Upper Tract Urothelial Carcinoma. J Clin \n" +
                "Oncol, 2017. 35: 852.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28045620\n" +
                "163. Jwa, E., et al. Adjuvant radiotherapy for stage III/IV urothelial carcinoma of the upper tract. \n" +
                "Anticancer Res, 2014. 34: 333.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/24403484\n" +
                "164. Ploussard, G., et al. Conditional survival after radical nephroureterectomy for upper tract carcinoma. \n" +
                "Eur Urol, 2015. 67: 803.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/25145551\n" +
                "165. Shigeta, K., et al. The Conditional Survival with Time of Intravesical Recurrence in Upper Tract \n" +
                "Urothelial Carcinoma. J Urol, 2017.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/28634017\n" +
                "166. Mandalapu, R.S., et al. Update of the ICUD-SIU consultation on upper tract urothelial carcinoma \n" +
                "2016: treatment of low-risk upper tract urothelial carcinoma. World J Urol, 2017. 35: 355.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/27233780\n" +
                "167. Bagley, D.H., et al. Ureteroscopic laser treatment of upper urinary tract neoplasms. World J Urol, \n" +
                "2010. 28: 143.\n" +
                "https://www.ncbi.nlm.nih.gov/pubmed/20229233\n" +
                "10. CONFLICT OF INTEREST\n" +
                "All members of the Non-Muscle-Invasive Bladder Cancer Guidelines working panel have provided disclosure\n" +
                "statements on all relationships that they have that might be perceived to be a potential source of a conflict of\n" +
                "interest. This information is publically accessible through the European Association of Urology website: http://\n" +
                "uroweb.org/guideline/upper-urinary-tract-urothelial-cell-carcinoma/. This guidelines document was developed\n" +
                "with the financial support of the European Association of Urology. No external sources of funding and support\n" +
                "have been involved. The EAU is a non-profit organisation, and funding is limited to administrative assistance\n" +
                "and travel and meeting expenses. No honoraria or other reimbursements have been provided.\n" +
                "UPPER URINARY TRACT UROTLELIAL CARCINOMA - LIMITED UPDATE MARCH 201828\n" +
                "11. CITATION INFORMATION\n" +
                "The format in which to cite the EAU Guidelines will vary depending on the style guide of the journal in which the \n" +
                "citation appears. Accordingly, the number of authors or whether, for instance, to include the publisher, location, \n" +
                "or an ISBN number may vary.  \n" +
                "The compilation of the complete Guidelines should be referenced as: \n" +
                "EAU Guidelines. Edn. presented at the EAU Annual Congress Copenhagen 2018. ISBN 978-94-92671-01-1.\n" +
                "If  a publisher and/or location is required, include: \n" +
                "EAU Guidelines Office, Arnhem, The Netherlands. http://uroweb.org/guidelines/compilations-of-all-guidelines/\n" +
                " to individual guidelines should be structured in the following way:\n" +
                "Contributors’ names. Title of resource. Publication type. ISBN. Publisher and publisher location, year.\n";
//        System.out.println(content.substring(a).replaceAll("guide.medlive.cn" ,"").replaceAll("Idiopathic Macular Hole PPP: ","").replaceAll("References",""));
        String pattern = "[0-9]+\\.[\\s\\S]*?[0-9]+.*?\\.|http.*?[0-9]+\\/.*?";
        Pattern re = Pattern.compile(pattern);
        Matcher m = re.matcher(content);
        while (m.find( )) {
            System.out.println("Found value: " + m.group() );
        }
    }
}
