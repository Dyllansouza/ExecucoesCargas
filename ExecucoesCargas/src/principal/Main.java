package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	public static void main(String[] args) throws IOException {

		Logger gravaScript = new Logs().Logger();
		FileInputStream excelFile = new FileInputStream(
				new File("C:/Automatizacao/ExecucoesCargas/Planilhas/carga.xlsx"));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();


		int numeroUltimaLinha = sheet.getLastRowNum();// começa em 0, e se a planilha estiver vazia também retorna zero

		int numeroTotalLinhas = sheet.getPhysicalNumberOfRows();// nao conta as linhas vazias
		int numeroTotalColunas = sheet.getRow(0).getLastCellNum();

//		gravaScript.info("Numero da Ultima linha: " + numeroUltimaLinha);
//		gravaScript.info("Numero de Colunas: " + numeroTotalColunas);

		Row rows = rowIterator.next();
		Iterator<Cell> cellIterator = rows.cellIterator();
		List<String> colunas = new ArrayList<String>();

		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			switch (cell.getColumnIndex()) {
			case 0:
				colunas.add(cell.getStringCellValue());
				break;
			case 1:
				colunas.add(cell.getStringCellValue());
				break;
			case 2:
				colunas.add(cell.getStringCellValue());
				break;
			default:
				break;

			}
		}

		int seqSite = 0;
		String id1 = "";
		String id2 = "";

		// ROW
		for (int i = 1; i < numeroUltimaLinha; i++) {
			// CELL
			for (int j = 0; j < numeroTotalColunas; j++) {
//				System.out.println("Linha = " + i +", Coluna = " + j);
				if (j == 0) {
					seqSite = (int) sheet.getRow(i).getCell(j).getNumericCellValue();
//					System.out.println( "SEQ_SITE = " + seqSite);
				}
				if (j == 1) {
					id1 = sheet.getRow(i).getCell(j).getStringCellValue();

//					System.out.println("ID_FINANCEIRO = " + id1);
				}
				if (j == 2) {
					id2 = sheet.getRow(i).getCell(j).getStringCellValue();


				}
				gravaScript.info("UPDATE SITE SET " + colunas.get(1) + " = '" + id1 + "', " + colunas.get(2) + " = '" + id2
						+ "' WHERE " + colunas.get(0) + " = '" + seqSite + "';");
			}

//		System.out.println(colunas);
		}
	}
}
