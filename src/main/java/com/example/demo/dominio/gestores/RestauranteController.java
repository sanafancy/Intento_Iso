package com.example.demo.dominio.gestores;

import com.example.demo.dominio.entidades.*;
import com.example.demo.persistencia.CartaMenuDAO;
import com.example.demo.persistencia.DireccionDAO;
import com.example.demo.persistencia.ItemMenuDAO;
import com.example.demo.persistencia.RestauranteDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteDAO restauranteDAO;

    @Autowired
    private CartaMenuDAO cartaMenuDAO;

    @Autowired
    private ItemMenuDAO itemMenuDAO;
    @Autowired
    private DireccionDAO direccionDAO;

    //registrarse
    @GetMapping("/registro")
    public String showRegistroForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "registroRestaurante";
    }

    @PostMapping("/registro")
    public String registerRestaurante(@ModelAttribute Restaurante restaurante) {
        restauranteDAO.save(restaurante);
        return "redirect:/restaurantes/login";
    }

    //iniciar sesion
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("restaurante", new Restaurante());
        return "loginRestaurante";
    }

    @PostMapping("/login")
    public String loginRestaurante(@ModelAttribute Restaurante restaurante, HttpSession session) {
        Optional<Restaurante> optionalRestaurante = restauranteDAO.findByEmailAndPass(restaurante.getEmail(), restaurante.getPass());
        if (optionalRestaurante.isPresent()) {
            session.setAttribute("restaurante", optionalRestaurante.get());
            return "redirect:/restaurantes/paginaRestaurante";
        } else {
            return "loginRestaurante";
        }
    }
    @GetMapping("/{id}")
    public String mostrarRestaurante(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("cliente") == null) {
            // Redirigir a login con el restaurante como parametro
            //return "redirect:/clientes/login?redirectUrl=/restaurantes/" + id;
            return "redirect:/clientes/login";
        }

        Optional<Restaurante> restauranteOpt = restauranteDAO.findById(id);
        if (restauranteOpt.isPresent()) {
            Restaurante restaurante = restauranteOpt.get();
            model.addAttribute("restaurante", restaurante);

            // Cargar cartas e ítems si existen
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            Map<Long, List<ItemMenu>> itemsPorMenu = new HashMap<>();
            for (CartaMenu carta : cartas) {
                List<ItemMenu> items = itemMenuDAO.findByCartaMenu(carta);
                itemsPorMenu.put(carta.getId(), items);
            }
            model.addAttribute("cartas", cartas);
            model.addAttribute("itemsPorMenu", itemsPorMenu);

            return "pedirDeRestaurante";  // Vista del restaurante
        }
        // Si no hay restaurante, vuelve a la página de búsqueda
        return "redirect:/";
    }

    //pagina principal de restaurante
    @GetMapping("/paginaRestaurante")
    public String paginaRestaurante() {
        return "paginaRestaurante";
    }

    //carta y las funciones del menu
    @GetMapping("/carta")
    public String carta(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);

            // Obtener los ítems de cada menú
            Map<Long, List<ItemMenu>> itemsPorMenu = new HashMap<>();
            for (CartaMenu carta : cartas) {
                List<ItemMenu> items = itemMenuDAO.findByCartaMenu(carta);
                itemsPorMenu.put(carta.getId(), items);
            }
            model.addAttribute("itemsPorMenu", itemsPorMenu);
        }
        return "carta";
    }

    @GetMapping("/anadirMenu")
    public String showAnadirMenuForm(Model model) {
        model.addAttribute("cartaMenu", new CartaMenu());
        return "anadirMenu";
    }

    @PostMapping("/anadirMenu")
    public String anadirMenu(@ModelAttribute CartaMenu cartaMenu, HttpSession session) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            cartaMenu.setRestaurante(restaurante);
            cartaMenuDAO.save(cartaMenu);
        }
        return "redirect:/restaurantes/carta";
    }
    @GetMapping("/eliminarMenu")
    public String showEliminarMenuPage(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);
        }
        return "eliminarMenu";
    }

    // Procesar la eliminación de menús seleccionados
    @PostMapping("/eliminarMenu")
    public String eliminarMenus(@RequestParam("menuIds") List<Long> menuIds) {
        for (Long id : menuIds) {
            cartaMenuDAO.deleteById(id);
        }
        return "redirect:/restaurantes/carta";
    }
    // Añadir metodo para mostrar la página de todos los menús
    @GetMapping("/todosMenus")
    public String showTodosMenus(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);
        }
        return "todosMenus";
    }

    // Añadir metodo para mostrar el formulario de añadir ítem
    @GetMapping("/anadirItem/{menuId}")
    public String showAnadirItemForm(@PathVariable Long menuId, Model model) {
        model.addAttribute("itemMenu", new ItemMenu());
        model.addAttribute("menuId", menuId);
        return "anadirItem";
    }

    // Añadir metodo para manejar la lógica de añadir ítem
    @PostMapping("/anadirItem")
    public String anadirItem(@ModelAttribute ItemMenu itemMenu, @RequestParam Long menuId) {
        Optional<CartaMenu> optionalCartaMenu = cartaMenuDAO.findById(menuId);
        if (optionalCartaMenu.isPresent()) {
            itemMenu.setCartaMenu(optionalCartaMenu.get());
            itemMenuDAO.save(itemMenu);
        }
        return "redirect:/restaurantes/carta";
    }

    @GetMapping("/eliminarItems")
    public String showEliminarItemsPage(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<CartaMenu> cartas = cartaMenuDAO.findByRestaurante(restaurante);
            model.addAttribute("cartas", cartas);
            // Obtener los ítems de cada menú
            Map<Long, List<ItemMenu>> itemsPorMenu = new HashMap<>();
            for (CartaMenu carta : cartas) {
                List<ItemMenu> items = itemMenuDAO.findByCartaMenu(carta);
                itemsPorMenu.put(carta.getId(), items);
            }
            model.addAttribute("itemsPorMenu", itemsPorMenu);
        }
        return "eliminarItems";
    }
    @PostMapping("/eliminarItems")
    public String eliminarItems(@RequestParam("itemIds") List<Long> itemIds) {
        for (Long id : itemIds) {
            itemMenuDAO.deleteById(id);
        }
        return "redirect:/restaurantes/carta";
    }

    // Direcciones
    @GetMapping("/direcciones")
    public String mostrarDirecciones(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<Direccion> direcciones = direccionDAO.findByRestaurante(restaurante);
            model.addAttribute("direcciones", direcciones);
        }
        return "direcciones";
    }

    // Mostrar formulario para añadir dirección
    @GetMapping("/anadirDireccion")
    public String mostrarFormularioAnadirDireccion(Model model) {
        model.addAttribute("direccion", new Direccion());
        return "anadirDireccion";
    }

    // Manejar la lógica para añadir dirección
    @PostMapping("/anadirDireccion")
    public String anadirDireccion(@ModelAttribute Direccion direccion, HttpSession session) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            direccion.setRestaurante(restaurante);
            direccionDAO.save(direccion);
        }
        return "redirect:/restaurantes/direcciones";
    }

    // Mostrar página para eliminar dirección
    @GetMapping("/eliminarDireccion")
    public String mostrarEliminarDireccion(HttpSession session, Model model) {
        Restaurante restaurante = (Restaurante) session.getAttribute("restaurante");
        if (restaurante != null) {
            List<Direccion> direcciones = direccionDAO.findByRestaurante(restaurante);
            model.addAttribute("direcciones", direcciones);
        }
        return "eliminarDireccion";
    }

    // Manejar la lógica para eliminar dirección
    @PostMapping("/eliminarDireccion")
    public String eliminarDireccion(@RequestParam("direccionId") Long direccionId) {
        direccionDAO.deleteById(direccionId);
        return "redirect:/restaurantes/direcciones";
    }
    //eliminar restaurante por completo
    @DeleteMapping("/eliminar")
    public ResponseEntity<Void> eliminarRestaurante(@SessionAttribute("restaurante") Restaurante restaurante) {
        restauranteDAO.delete(restaurante);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/addItem")
    @ResponseBody
    public ResponseEntity<String> addItemToCart(@PathVariable Long id, @RequestParam Long itemId, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debe iniciar sesión para añadir items.");
        }

        Optional<Restaurante> restauranteOpt = restauranteDAO.findById(id);
        if (restauranteOpt.isPresent()) {
            Optional<ItemMenu> itemOpt = itemMenuDAO.findById(itemId);
            if (itemOpt.isPresent()) {
                // Lógica para añadir el ítem al pedido (por simplificar, usarías un servicio o DAO aquí)
                // ...
                return ResponseEntity.ok("Ítem añadido al pedido.");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurante o ítem no encontrado.");
    }
}