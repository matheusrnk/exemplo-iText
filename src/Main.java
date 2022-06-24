import java.io.FileNotFoundException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class Main {
    public static void main(String[] args) {

        String[] semestres = {"2022-1", "2022-2"};
        String[] disciplinas = {"POO", "EDA1", "PAP", "LFA", "SOP", "ETI"};
        String[] medias = {"7.5", "8.0", "6.7", "5.6", "9.2", "9.5"};

        try {
            //Criando um objeto do tipo PdfWriter passando o seu nome/caminho
            String dest = "minhasNotas.pdf";
            PdfWriter writer = new PdfWriter(dest);

            //Criando um objeto do tipo PdfDocument passando o "writer" como parâmetro
            PdfDocument pdf = new PdfDocument(writer);

            //Criando um objeto do tipo Documento passando o "pdf" como parâmetro
            Document doc = new Document(pdf);

            //Criando uma tabela
            float[] pointColumnWidths = {150f, 150f, 150f}; // Aqui refere ao tamanho e quantidade das colunas
            Table table = new Table(pointColumnWidths); // Aqui é passado as colunas criadas

            //Nome das colunas
            table.addCell(new Cell().add(new Paragraph("Semestre")));
            table.addCell(new Cell().add(new Paragraph("Disciplina")));
            table.addCell(new Cell().add(new Paragraph("Média Final")));

            int inicio = 0;
            int fim = 3;
            for(int i=0; i<semestres.length; i++){
                for(int j=inicio; j<fim; j++){
                    //Ordem das informações de cada coluna em uma linha
                    table.addCell(new Cell().add(new Paragraph(semestres[i])));
                    table.addCell(new Cell().add(new Paragraph(disciplinas[j])));
                    table.addCell(new Cell().add(new Paragraph(medias[j])));
                }
                inicio = fim;
                fim += fim;
            }

            //Adicionando a tabela criada ao documento
            doc.add(table);

            //Fechando o documento
            doc.close();

            System.out.println("Tabela criada com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}