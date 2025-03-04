package Services;

import DatosDB.QueryComponente;
import DatosDB.QueryComputadora;
import DatosDB.QueryEnsamblajePieza;
import DatosDB.QueryUsuario;
import Modelos.Componente;
import Modelos.Computadora;
import Modelos.EnsamblajePieza;
import Modelos.Rol;
import Modelos.Usuario;
import java.util.ArrayList;

public class CargaArchivoService {

    public ArrayList<String> CargarArchivo(ArrayList<String> lineas) {
        ArrayList<String> errores = new ArrayList<>(); // Lista para almacenar errores

        QueryComponente queryComponente = new QueryComponente();
        QueryUsuario queryUsuario = new QueryUsuario();
        QueryComputadora queryComputadora = new QueryComputadora();
        QueryEnsamblajePieza queryEnsamblaje = new QueryEnsamblajePieza();

        for (int i = 0; i < lineas.size(); i++) {
            String recuperarPalabra = lineas.get(i);

            try {
                int inicio = recuperarPalabra.indexOf("(") + 1;
                int fin = recuperarPalabra.lastIndexOf(")");
                if (inicio == 0 || fin == -1) {
                    errores.add("Error en la línea " + (i + 1) + ": formato incorrecto.");
                    continue;
                }

                String contenido = recuperarPalabra.substring(inicio, fin);
                String[] valores = contenido.split(",");

                if (recuperarPalabra.startsWith("USUARIO")) {
                    if (valores.length < 3) {
                        errores.add("Error en la línea " + (i + 1) + ": falta información del usuario.");
                        continue;
                    }

                    String usuario = valores[0].replace("\"", "").trim();
                    String password = valores[1].replace("\"", "").trim();
                    int rol = Integer.parseInt(valores[2].trim());

                    Usuario user = new Usuario(-1, usuario, password, new Rol(rol, ""));
                    queryUsuario.crear(user);

                } else if (recuperarPalabra.startsWith("PIEZA")) {
                    if (valores.length < 2) {
                        errores.add("Error en la línea " + (i + 1) + ": falta información de la pieza.");
                        continue;
                    }

                    String nombre = valores[0].replace("\"", "").trim();
                    double costo = Double.parseDouble(valores[1].trim());

                    Componente componente = new Componente(-1, nombre, costo, -1);
                    queryComponente.crear(componente);

                } else if (recuperarPalabra.startsWith("COMPUTADORA")) {
                    if (valores.length < 2) {
                        errores.add("Error en la línea " + (i + 1) + ": falta información de la computadora.");
                        continue;
                    }

                    String nombreComputadora = valores[0].replace("\"", "").trim();
                    double precioComputadora = Double.parseDouble(valores[1].trim());

                    Computadora computadora = new Computadora(-1, nombreComputadora, precioComputadora);
                    queryComputadora.crear(computadora);

                } else if (recuperarPalabra.startsWith("ENSAMBLE_PIEZA")) {
                    if (valores.length < 3) {
                        errores.add("Error en la línea " + (i + 1) + ": falta información del ensamble de pieza.");
                        continue;
                    }

                    String nombreComputadora = valores[0].replace("\"", "").trim();
                    String nombrePieza = valores[1].replace("\"", "").trim();
                    int cantidad = Integer.parseInt(valores[2].trim());

                    Computadora computadora = queryComputadora.encontrarPorNombre(nombreComputadora);
                    Componente componente = queryComponente.encontrarPorNombre(nombrePieza);

                    if (computadora == null) {
                        errores.add("Error en la línea " + (i + 1) + ": la computadora '" + nombreComputadora + "' no existe.");
                        continue;
                    }
                    if (componente == null) {
                        errores.add("Error en la línea " + (i + 1) + ": la pieza '" + nombrePieza + "' no existe.");
                        continue;
                    }

                    EnsamblajePieza piezaEnsamble = new EnsamblajePieza(-1, computadora, componente, cantidad);
                    queryEnsamblaje.crear(piezaEnsamble);

                } else if (recuperarPalabra.startsWith("ENSAMBLAR_COMPUTADORA")) {
                    if (valores.length < 3) {
                        errores.add("Error en la línea " + (i + 1) + ": falta información para ensamblar computadora.");
                        continue;
                    }

                    String tipoComputadora = valores[0].replace("\"", "").trim();
                    String usuario = valores[1].replace("\"", "").trim();
                    String fecha = valores[2].replace("\"", "").trim();

                    Computadora compu = queryComputadora.encontrarPorNombre(tipoComputadora);
                    Usuario nombre = queryUsuario.encontrarPorNombre(usuario);

                    if (compu == null) {
                        errores.add("Error en la línea " + (i + 1) + ": la computadora '" + tipoComputadora + "' no existe.");
                    }
                    if (nombre == null) {
                        errores.add("Error en la línea " + (i + 1) + ": el usuario '" + usuario + "' no existe.");
                    }
                } else {
                    errores.add("Error en la línea " + (i + 1) + ": comando desconocido.");
                }
            } catch (NumberFormatException e) {
                errores.add("Error en la línea " + (i + 1) + ": formato numérico incorrecto (" + e.getMessage() + ").");
            } catch (Exception e) {
                errores.add("Error en la línea " + (i + 1) + ": " + e.getMessage());
            }
        }

        // Si hay errores, los imprimimos (o puedes retornarlos)
        if (!errores.isEmpty()) {
            System.out.println("Errores encontrados durante la carga:");
            for (String error : errores) {
                System.out.println(error);
            }
        }

        return errores; // Retornar la lista de errores para manejar en otro lugar si es necesario
    }
}
