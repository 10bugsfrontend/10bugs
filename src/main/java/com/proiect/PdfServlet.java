package com.proiect;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/raportFacturi.pdf")
public class PdfServlet extends HttpServlet {

	private static final long serialVersionUID = -2709505566562624037L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		File file = new File("./src/main/java/com/utils/raportFacturi.pdf");
		response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"raportFacturi.pdf\"");
		Files.copy(file.toPath(), response.getOutputStream());
	}
}
