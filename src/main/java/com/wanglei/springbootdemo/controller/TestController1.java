package com.wanglei.springbootdemo.controller;

import com.wanglei.springbootdemo.util.PdfProcessUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController1 {
    public static void main(String[] args) {
        String content = "\n" +
                "1. Promise Ariyo, Bassem Zayed, Victoria Riese,\n" +
                "Blair Anton, Asad Latif, Claire Kilpatrick, et al.\n" +
                "Implementation Strategies to Reduce Surgical\n" +
                "Site Infections: A Systematic Review. Infect\n" +
                "Control Hosp Epidemiol 2019, in press.\n" +
                "2. Allegranzi B, Aiken AM, Zeynep Kubilay N, \n" +
                "et al. A multimodal infection control and\n" +
                "patient safety intervention to reduce surgical\n" +
                "site infections in Africa: a multicentre,\n" +
                "before/after, cohort study. The Lancet\n" +
                "Infectious diseases. 2018;18(5):507-515.\n" +
                "3. Preventing surgical site infections:\n" +
                "implementation approaches for evidence-based\n" +
                "recommendations Geneva: World Health\n" +
                "Organization; 2018\n" +
                "(https://www.who.int/infectionprevention/tools\n" +
                "/surgical/en/, accessed 11 December 2018).\n" +
                "4. Implementation manual to prevent surgical site\n" +
                "infections at the facility level – turning\n" +
                "recommendations into practice. Geneva: World\n" +
                "Health Organization; 2018\n" +
                "(https://www.who.int/infectionprevention/tools\n" +
                "/surgical/en/, accessed 21 December 2018).\n" +
                "http:///\n" +
                "6.1  Guidelines Development Group \n" +
                "Professor Hanan H Balkhy\n" +
                "WHO Collaborating Centre and GCC Center \n" +
                "for Infection Control \n" +
                "Infection Prevention and Control Department \n" +
                "King Saud Bin Abdulaziz University for Health\n" +
                "Sciences\n" +
                "PO Box 22490\n" +
                "Riyadh 11426, Kingdom of Saudi Arabia\n" +
                "BalkhyH@ngha.med.sa\n" +
                "Professor F. Javier Belda  \n" +
                "University of Valencia\n" +
                "Department of Anesthesia\n" +
                "Valencia, Spain\n" +
                "Fco.javier.belda@uv.es\n" +
                "Professor Sean Berenholtz  \n" +
                "Armstrong Institute for Patient Safety and Quality\n" +
                "Johns Hopkins Medicine \n" +
                "Baltimore, MD, USA\n" +
                "sberenho@jhmi.edu\n" +
                "Professor Marja A Boermeester \n" +
                "Department of Surgery\n" +
                "Amsterdam Gastroenterology and Metabolism\n" +
                "Amsterdam Infection and Immunity\n" +
                "Amsterdam UMC\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105AZ Amsterdam, the Netherlands\n" +
                "m.a.boermeester@amc.uva.nl\n" +
                "Dr Nizam Damani \n" +
                "Craigavon Area Hospital\n" +
                "Portadown, Craigavon, Co Armagh, UK\n" +
                "nizdamani@aol.com\n" +
                "Professor E. Patchen Dellinger \n" +
                "University of Washington\n" +
                "Box 356410 Room BB 441\n" +
                "1959 N.E. Pacific Street\n" +
                "Seattle, WA 98195-6410, USA\n" +
                "patch@u.washington.edu\n" +
                "Dr Mazen Ferwana \n" +
                "National & Gulf Centre for Evidence Based Health\n" +
                "Practice\n" +
                "King Saud Bin Abdulaziz University for Health\n" +
                "Sciences MNGHA\n" +
                "Riyadh, Kingdom of Saudi Arabia\n" +
                "ferwanam@ngha.med.sa\n" +
                "Professor Petra Gastmeier \n" +
                "Institute of Hygiene and Environmental Medicine \n" +
                "Charité-University Medicine Berlin\n" +
                "Hindenburgdamm 27\n" +
                "12203 Berlin, Germany\n" +
                "petra.gastmeier@charite.de\n" +
                "Professor Robert Greif \n" +
                "Department of Anesthesiology and Pain Therapy\n" +
                "Bern University Hospital\n" +
                "University of Bern\n" +
                "Bern, Switzerland\n" +
                "robert.greif@insel.ch\n" +
                "Dr Xavier Guirao \n" +
                "Endocrine & Head and Neck Surgery\n" +
                "Corporacifi Sanitaria del Parc Taul› de Sabadell\n" +
                "University Hospital Sabadell\n" +
                "Barcelona, Spain\n" +
                "xguirao@gmail.com\n" +
                "Ms Regina Namata Kamoga \n" +
                "Community Health and Information Network\n" +
                "(CHAIN)\n" +
                "Kampala, Uganda\n" +
                "namata.kamoga@gmail.com\n" +
                "6. ANNEXES\n" +
                "180Global Guidelines for the Prevention of Surgical Site Infection\n" +
                "http:///\n" +
                "181 6. Annexes\n" +
                "Dr Asad Latif\n" +
                "Armstrong Institute for Patient Safety and Quality\n" +
                "Johns Hopkins Medicine\n" +
                "Baltimore, MD, USA\n" +
                "aaltif1@jhmi.edu\n" +
                "Professor Nordiah Awang Jalil \n" +
                "Department of Medical Microbiology &\n" +
                "Immunology and Head of Infection Control Unit\n" +
                "Universiti Kebangsaan Malaysia Medical Centre\n" +
                "Jalan Yaacob Latif, Bandar Tun Razak 56000 Cheras\n" +
                "Kuala Lumpur, Malaysia\n" +
                "nordiah@ppukm.ukm.edu.my\n" +
                "Professor Fauzia Khan \n" +
                "Aga Khan University\n" +
                "Karachi, Pakistan\n" +
                "Fauzia.khan@aku.edu\n" +
                "Ms Robinah Kaitiritimba \n" +
                "Uganda National Health Consumers’ Organisation \n" +
                "Plot 744 Namuli Road, Bukoto, P.O Box 70095\n" +
                "Kampala Uganda\n" +
                "rkitungi@yahoo.com \n" +
                "Ms Claire Kilpatrick \n" +
                "KS Healthcare Consulting\n" +
                "London, UK \n" +
                "clairekilpatrick.ck@googlemail.com  \n" +
                "Dr Janet Martin \n" +
                "World Federation of Societies of Anaesthesiologists\n" +
                "(WFSA)\n" +
                "Anesthesia and Perioperative Medicine\n" +
                "Schulich School of Medicine & Dentistry\n" +
                "Western University\n" +
                "London, Ontario, Canada\n" +
                "janet.martin@lhsc.on.ca\n" +
                "Professor Shaheen Mehtar \n" +
                "Tygerberg Hospital & Stellenbosch University\n" +
                "Tygerberg 7505\n" +
                "Cape Town, Republic of South Africa\n" +
                "smehtar@sun.ac.za\n" +
                "Dr Babacar Ndoye \n" +
                "Infection Control Africa Network\n" +
                "Dakar, Senegal\n" +
                "basendoye2@yahoo.fr\n" +
                "Dr Peter M Nthumba \n" +
                "Plastic, Reconstructive and Hand Surgery Unit\n" +
                "Medical Education and Research Unit\n" +
                "AIC Kijabe Hospital\n" +
                "Kijabe, Kenya\n" +
                "nthumba@gmail.com\n" +
                "Dr Jannicke Mellin-Olsen \n" +
                "WFSA \n" +
                "London, United Kingdom\n" +
                "mellinolsen@gmail.com\n" +
                "Professor Bisola Onajin Obembe \n" +
                "WFSA\n" +
                "Department of Anaesthesiology\n" +
                "College of Health Sciences\n" +
                "University of Port Harcourt\n" +
                "Rivers State, Nigeria\n" +
                "bisolaobembe@yahoo.co.uk\n" +
                "Professor Akca Ozan\n" +
                "Department of Anaesthesiology and Perioperative\n" +
                "Medecine\n" +
                "University of Louisville, USA\n" +
                "ozan.akca@louisville.edu\n" +
                "Dr Leonardo Pagani \n" +
                "Infectious Diseases Unit\n" +
                "Bolzano Central Hospital\n" +
                "Via L. Boehler, 5\n" +
                "39100 Bolzano, Italy\n" +
                "lpagani.id@gmail.com \n" +
                "Professor Didier Pittet\n" +
                "Infection Control Programme and\n" +
                "WHO Collaborating Centre on Patient Safety \n" +
                "University of Geneva Hospitals and Faculty of\n" +
                "Medicine \n" +
                "4 Rue Gabrielle-Perret-Gentil\n" +
                "1211 Geneva 14, Switzerland\n" +
                "Didier.Pittet@hcuge.ch\n" +
                "Professor Jianan Ren \n" +
                "Research Institute of General Surgery\n" +
                "Jinling Hospital\n" +
                "Medical School of Nanjing University \n" +
                "Nanjing, 210002, People’s Republic of China\n" +
                "jan@medmail.com.cn\n" +
                "Professor Joseph S Solomkin \n" +
                "University of Cincinnati College of Medicine\n" +
                "231 Albert B. Sabin Way\n" +
                "Cincinnati OH 45267-0558, USA /\n" +
                "CEO, OASIS Global, 6005 Given Road\n" +
                "Cincinnati, OH 45243, USA\n" +
                "solomkjs@ucmail.uc.edu\n" +
                "Ms Akeau Unahalekhaka \n" +
                "Faculty of Nursing\n" +
                "Chiang Mai University\n" +
                "A Muang \n" +
                "Chiang Mai, 50200 Thailand\n" +
                "akeau@hotmail.com\n" +
                "http:///\n" +
                "Professor Dr Andreas Widmer\n" +
                "Division of Infectious Diseases & Hospital\n" +
                "Epidemiology and Infection Control\n" +
                "University of Basel Hospital and Clinics\n" +
                "4031 Basel, Switzerland\n" +
                "Andreas.Widmer@usb.ch\n" +
                "Professor Matthias Egger\n" +
                "Methodologist\n" +
                "Institute of Social and Preventive Medicine\n" +
                "University of Bern\n" +
                "Finkenhubelweg 11\n" +
                "3012 Bern, Switzerland\n" +
                "egger@ispm.unibe.ch\n" +
                "6.2  WHO Steering Group\n" +
                "Dr Benedetta Allegranzi\n" +
                "Infection Prevention and Control Global Unit\n" +
                "Department of Service Delivery and Safety\n" +
                "allegranzib@who.int\n" +
                "Dr Edward Kelley\n" +
                "Department of Service Delivery and Safety\n" +
                "kelleye@who.int\n" +
                "Dr Walter Johnson\n" +
                "Department of Service Delivery and Safety\n" +
                "johnsonw@who.int\n" +
                "Dr Bassim Zayed\n" +
                "Department of Service Delivery and Safety\n" +
                "baszayed99@yahoo.com\n" +
                "Dr Sergey Eremin\n" +
                "Department of Pandemic and Epidemic Diseases\n" +
                "eremins@who.int\n" +
                "Dr Valeska Stempliuk\n" +
                "Regional Office for the Americas\n" +
                "stempliv@paho.org\n" +
                "6.3  Systematic Reviews Expert Group\n" +
                "Dr Benedetta Allegranzi\n" +
                "Infection Prevention and Control Global Unit\n" +
                "Service Delivery and Safety, HIS\n" +
                "WHO\n" +
                "20 Avenue  Appia\n" +
                "1211 Geneva 27, Switzerland\n" +
                "allegranzib@who.int\n" +
                "Dr Jasper Atema\n" +
                "Department of Surgery (G4-142)\n" +
                "Academic Medical Center\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105AZ Amsterdam, the Netherlands\n" +
                "j.j.atema@amc.nl\n" +
                "Professor Sean Berenholtz\n" +
                "Armstrong Institute for Patient Safety and Quality\n" +
                "Johns Hopkins Medicine\n" +
                "Baltimore, MD, USA\n" +
                "sberenho@jhmi.edu \n" +
                "Dr Peter Bischoff \n" +
                "Institute of Hygiene and Environmental Medicine, \n" +
                "Charité-University Medicine Berlin\n" +
                "Hindenburgdamm 27\n" +
                "12203, Berlin, Germany\n" +
                "peter.bischoff@charite.de\n" +
                "Professor Marja A. Boermeester \n" +
                "Department of Surgery (G4-132.1)\n" +
                "Academic Medical Center\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105AZ Amsterdam, the Netherlands\n" +
                "m.a.boermeester@amc.uva.nl\n" +
                "Ms Quirine Boldingh\n" +
                "Department of Surgery (G4-132.1)\n" +
                "Academic Medical Center\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105AZ Amsterdam, the Netherlands\n" +
                "j.q.boldingh@amc.uva.nl\n" +
                "Dr Sarah Gans\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105AZ Amsterdam, the Netherlands\n" +
                "s.l.gans@amc.uva.nl\n" +
                "182Global Guidelines for the Prevention of Surgical Site Infection\n" +
                "http:///\n" +
                "183 6. Annexes\n" +
                "Professor Petra Gastmeier\n" +
                "Institute of Hygiene and Environmental Medicine \n" +
                "Charité-University Medicine Berlin\n" +
                "Hindenburgdamm 27\n" +
                "12203 Berlin, Germany\n" +
                "petra.gastmeier@charite.de\n" +
                "Dr Stacey Gomez\n" +
                "OASIS Global\n" +
                "6005 Given Road\n" +
                "Cincinnati OH 45243, USA\n" +
                "gomes.stacey@gmail.com\n" +
                "Dr Xavier Guirao\n" +
                "Unit of Endocrine & Head and Neck Surgery\n" +
                "Unit of Surgical Infection Support\n" +
                "Assistant Professor in Surgery, UAB\n" +
                "Hospital Universitari Parc Tauli, Sabadell\n" +
                "Barcelona, Spain\n" +
                "xguirao@gmail.com\n" +
                "Dr Stijn de Jonge\n" +
                "Department of Surgery (G4-129)\n" +
                "Academic Medical Center\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105AZ Amsterdam, the Netherlands\n" +
                "s.w.dejonge@amc.uva.nl\n" +
                "Professor Jan Kluytmans\n" +
                "Amphia Hospital Breda\n" +
                "Laboratory for Microbiology and Infection Control\n" +
                "4800 RK Breda, the Netherlands\n" +
                "jankluytmans@gmail.com\n" +
                "Dr Zeynep Kubilay \n" +
                "Infection Prevention & Control Global Unit, \n" +
                "Service Delivery and Safety, HIS\n" +
                "WHO\n" +
                "20 Avenue Appia\n" +
                "1211 Geneva 27, Switzerland\n" +
                "zepella@hotmail.com\n" +
                "Ms Caroline Landelle\n" +
                "Infection Control Programme and WHO\n" +
                "Collaborating Centre on Patient Safety\n" +
                "University of Geneva Hospitals \n" +
                "4 Rue Gabrielle-Perret-Gentil\n" +
                "1211 Geneva 14, Switzerland\n" +
                "caroline.landelle@gmail.com\n" +
                "Dr Asad Latif \n" +
                "Armstrong Institute for Patient Safety and Quality\n" +
                "Johns Hopkins Medicine\n" +
                "Baltimore, MD, USA\n" +
                "alatif1@jhmi.edu\n" +
                "Professor Yoon K Loke \n" +
                "Norwich Medical School, University of East Anglia\n" +
                "Norwich, UK \n" +
                "Y.Loke@uea.ac.uk \n" +
                "Mr. Adam Peel\n" +
                "Norwich Medical School, University of East Anglia\n" +
                "Norwich, UK\n" +
                "a.peel@uea.ac.uk\n" +
                "Dr Katharina Mattishent\n" +
                "Norwich Medical School, University of East Anglia\n" +
                "Norwich, UK\n" +
                "k.mattishent@uea.ac.uk \n" +
                "Ms Sandra Pequeno\n" +
                "Iberoamerican Cochrane Center\n" +
                "Biomedical Institute, IIB-Sant Pau-CIBERESP\n" +
                "Barcelona, Spain\n" +
                "Professor Didier Pittet\n" +
                "Infection Control Programme and WHO\n" +
                "Collaborating Centre on Patient Safety \n" +
                "University of Geneva Hospitals and Faculty \n" +
                "of Medicine \n" +
                "4 Rue Gabrielle-Perret-Gentil\n" +
                "1211 Geneva 14, Switzerland\n" +
                "Didier.Pittet@hcuge.ch\n" +
                "Professor Jianan Ren\n" +
                "Research Institute of General Surgery\n" +
                "Jinling Hospital, Medical School of Nanjing\n" +
                "University \n" +
                "305 East Zhongshan Rd\n" +
                "Nanjing 210002, People’s Republic of China\n" +
                "jan@medmail.com.cn\n" +
                "Dr Miranda van Rijen \n" +
                "Amphia Ziekenhuis, Molengracht\n" +
                "PO Box 90158\n" +
                "4800 RK Breda, the Netherlands\n" +
                "mvanrijen@amphia.nl \n" +
                "Dr. Ashnish Sinha\n" +
                "Norwich Medical School, University of East Anglia\n" +
                "Norwich, UK\n" +
                "ashnish@doctors.org.uk\n" +
                "Professor Joseph S. Solomkin \n" +
                "University of Cincinnati College of Medicine\n" +
                "231 Albert B. Sabin Way\n" +
                "Cincinnati, OH 45267-0558, USA\n" +
                "CEO, OASIS Global\n" +
                "6005 Given Road\n" +
                "Cincinnati, OH 45243, USA\n" +
                "solomkjs@ucmail.uc.edu\n" +
                "http:///\n" +
                "Dr. Menaka Thavarajah\n" +
                "Norwich Medical School, University of East Anglia\n" +
                "Norwich, UK\n" +
                "menakathavarajah@yahoo.co.uk\n" +
                "Dr Fleur de Vries\n" +
                "Department of Surgery (G4-133)\n" +
                "Academic Medical Center\n" +
                "University of Amsterdam\n" +
                "Meibergdreef 9\n" +
                "1105 AZ Amsterdam, the Netherlands\n" +
                "f.e.devries@amc.uva.nl\n" +
                "Dr Xiuwen Wu\n" +
                "Department of Surgery\n" +
                "Jinling Hospital, Medical School of Nanjing\n" +
                "University\n" +
                "305 East Zhongshan Road\n" +
                "Nanjing 210002, People’s Republic of China\n" +
                "xiuwenwoo@gmail.com\n" +
                "Dr Bassim Zayed\n" +
                "Antimicrobial Resistance and Infection Prevention\n" +
                "and Control Team\n" +
                "WHO Eastern Mediterranean Regional Office\n" +
                "Cairo, Egypt \n" +
                "6.4  External Peer Review Group \n" +
                "Professor Emmanuel A. Ameh\n" +
                "Department of Surgery \n" +
                "Ahmadu Bello University & Ahmadu Bello\n" +
                "University Teaching Hospital\n" +
                "Zaria, Nigeria\n" +
                "eaameh@yahoo.co.uk\n" +
                "Professor Valerie Robertson \n" +
                "Zimbabwe Infection Prevention and Control \n" +
                "Project \n" +
                "Department of Medical Microbiology, \n" +
                "University of Zimbabwe \n" +
                "Harare, Zimbabwe \n" +
                "robertson@uz-ucsf.co.zw\n" +
                "Dr Fernando Ota›za \n" +
                "IPC Unit\n" +
                "Departamento de Calidad y Seguridad del Paciente\n" +
                "Subsecretar›a de Redes Asistenciales\n" +
                "Ministerio de Salud\n" +
                "Santiago, Chile\n" +
                "fotaiza@minsal.cl\n" +
                "Professor Kamal Itani\n" +
                "General Surgery \n" +
                "VA Boston Healthcare System & Boston University\n" +
                "School of Medicine \n" +
                "Boston, USA\n" +
                "Kamal.Itani@va.gov\n" +
                "Dr Ilker Uçkay\n" +
                "Infectious Diseases and Orthopaedic Surgery, \n" +
                "University of Geneva Hospitals\n" +
                "4 Rue Gabrielle Perret-Gentil\n" +
                "1211 Geneva 14, Switzerland.\n" +
                "Ilker.Uckay@hcuge.ch\n" +
                "184Global Guidelines for the Prevention of Surgical Site Infection\n" +
                "http:///\n" +
                "World Health Organization\n" +
                "20 Avenue Appia\n" +
                "CH-1211 Geneva 27\n" +
                "Switzerland\n" +
                "Tel.: +41 22 791 5060\n" +
                "Email: savelives@who.int\n" +
                "Please visit us at:\n" +
                "www.who.int/gpsc/en\n" +
                "http:///\n";
//        System.out.println(content.substring(a).replaceAll("guide.medlive.cn" ,"").replaceAll("Idiopathic Macular Hole PPP: ","").replaceAll("References",""));
        String pattern = "[0-9]+\\.[\\s\\S]*?[0-9]+.*?\\.|http.*?[0-9]+\\/.*?";
        Pattern re = Pattern.compile(pattern);
        Matcher m = re.matcher(content);
        while (m.find( )) {
            System.out.println("Found value: " + m.group() );
        }
    }
}
