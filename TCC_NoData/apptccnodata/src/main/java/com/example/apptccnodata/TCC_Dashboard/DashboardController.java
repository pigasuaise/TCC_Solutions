package com.example.apptccnodata.TCC_Dashboard;

import java.io.ByteArrayOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class DashboardController {
    
    @GetMapping("/tccDashboard")
    public String showDash() {
        return "/tccDashboard";
    }

    @GetMapping("/back2Dashboard")
    public String backToDash() {
        return "redirect:/dashboard";
    }

    @PostMapping("/enviarTexto")
    public ResponseEntity<byte[]> enviarTexto(@RequestParam String nomeArea, @RequestParam String tutorArea, @RequestParam String introducao, @RequestParam String fundamentacaoTeorica, @RequestParam String metodologia, @RequestParam String resultados, @RequestParam String referencias, @RequestParam String tituloArea) throws DocumentException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document doc = new Document();

        // Fontes
        Font fonteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
        Font fonteNegrito = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font fonteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        Paragraph titulo = new Paragraph(tituloArea);
        Paragraph intro = new Paragraph(introducao);   
        Paragraph fund = new Paragraph(fundamentacaoTeorica);
        Paragraph met = new Paragraph(metodologia);
        Paragraph res = new Paragraph(resultados);
        Paragraph ref = new Paragraph(referencias);
        PdfWriter.getInstance(doc, baos);

        doc.open();

        Paragraph paraTitulo = new Paragraph(tituloArea, fonteTitulo);
        titulo.setSpacingAfter(10f);
        titulo.setSpacingBefore(10f);
        intro.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(paraTitulo);        
        intro.setSpacingAfter(10f);
        intro.setSpacingBefore(10f);
        
        
        // Parte de introdução
        Paragraph introTitle = new Paragraph("Introdução", fonteNegrito);
        introTitle.setSpacingAfter(10f);
        introTitle.setSpacingBefore(10f);
        doc.add(introTitle);
        intro.setSpacingAfter(10f);
        intro.setSpacingBefore(10f);
        intro.setFont(fonteNormal);
        intro.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        doc.add(intro);
        doc.add(new Paragraph(Chunk.NEWLINE));

        // Parte de fundamentação teórica
        Paragraph fundTitle = new Paragraph("Fundamentação Teórica", fonteNegrito);
        fundTitle.setSpacingAfter(10f);
        fundTitle.setSpacingBefore(10f);
        doc.add(fundTitle);
        fund.setSpacingAfter(10f);
        fund.setSpacingBefore(10f);
        fund.setFont(fonteNormal);
        fund.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        doc.add(fund);
        doc.add(new Paragraph(Chunk.NEWLINE));

        // Parte de metodologia
        Paragraph metTitle = new Paragraph("Metodologia", fonteNegrito);
        metTitle.setSpacingAfter(10f);
        metTitle.setSpacingBefore(10f);
        doc.add(metTitle);
        met.setSpacingAfter(10f);
        met.setSpacingBefore(10f);
        met.setFont(fonteNormal);
        met.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        doc.add(met);
        doc.add(new Paragraph(Chunk.NEWLINE));

        // Parte de resultados
        Paragraph resTitle = new Paragraph("Resultados", fonteNegrito);
        resTitle.setSpacingAfter(10f);
        resTitle.setSpacingBefore(10f);
        doc.add(resTitle);
        res.setSpacingAfter(10f);
        res.setSpacingBefore(10f);
        res.setFont(fonteNormal);
        res.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        doc.add(res);
        doc.add(new Paragraph(Chunk.NEWLINE));


        // Parte de referências
        Paragraph refTitle = new Paragraph("Referências", fonteNegrito);
        refTitle.setSpacingAfter(10f);
        refTitle.setSpacingBefore(10f);
        doc.add(refTitle);
        ref.setSpacingAfter(10f);
        ref.setSpacingBefore(10f);
        ref.setFont(fonteNormal);
        ref.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        doc.add(ref);
        doc.add(new Paragraph(Chunk.NEWLINE));

        // Parte de nomes e tutores
        Paragraph nome = new Paragraph();
        nome.add(new Chunk("Nomes: ", fonteNegrito));
        nome.add(new Chunk(nomeArea, fonteNormal));
        doc.add(nome);

        Paragraph tutorTitle = new Paragraph();
        tutorTitle.add(new Chunk("Tutores: ", fonteNegrito));
        tutorTitle.add(new Chunk(tutorArea, fonteNormal));
        doc.add(tutorTitle);
        doc.add(new Paragraph(Chunk.NEWLINE));

        doc.close();

        HttpHeaders header = new HttpHeaders();
        header.add("Content", "attachment; filename=document.pdf");
        header.add("Content-Type", "application/pdf");

        return new ResponseEntity<>(baos.toByteArray(), header, HttpStatus.OK);
    }
}

// @RequestParam String usuario, @RequestParam String senha, Model model