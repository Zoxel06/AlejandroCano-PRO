import com.itextpdf.text.Document
import com.itextpdf.text.Element
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream

fun main() {
    val fileName = "mi_archivo.pdf" // Nombre del archivo PDF

    // Crear un nuevo documento
    val document = Document()

    // Inicializar el objeto PdfWriter para escribir en el documento
    val writer = PdfWriter.getInstance(document, FileOutputStream(fileName))

    // Abrir el documento
    document.open()

    // Escribir contenido en el documento
    val paragraph = Paragraph("¡Hola, mundo!")
    paragraph.alignment = Element.ALIGN_CENTER
    document.add(paragraph)

    // Cerrar el documento
    document.close()

    println("Archivo PDF creado exitosamente.")
}