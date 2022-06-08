package cap.workwalk.controller;

import cap.workwalk.entity.Pet;
import cap.workwalk.entity.User;
import cap.workwalk.repository.PetRepository;
import cap.workwalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @GetMapping("/puppy")
    public String searchPuppy(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));

        List<Pet> sameAddressPetList = petRepository.findListByAddress(user.getSearchAddress());
        System.out.println(" 같은동 펫 리스트 : " + sameAddressPetList);

        model.addAttribute("userAddrDong", user.getSearchAddress());
        model.addAttribute("userAddr", user.getAddress());
        model.addAttribute("sameAddressPetList", sameAddressPetList);
        return "searchs/puppysearch";
    }

    @GetMapping("/walker")
    public String searchWalker(Model model, Principal principal) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));


        List<User> sameAddressUserList = userRepository.findListByAddress(user.getSearchAddress());
        System.out.println(" 같은동 워커 리스트 : " + sameAddressUserList);

        model.addAttribute("userAddrDong", user.getSearchAddress());
        model.addAttribute("userAddr", user.getAddress());
        model.addAttribute("sameAddressUserList", sameAddressUserList);
        return "searchs/walkersearch";
    }


    @PostMapping("/filter/puppy")
    public String searchFilterPuppy(@RequestParam("gender") String gender, @RequestParam("neutering") String neutering,
                                    @RequestParam("vaccination") String vaccination, Principal principal, Model model) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));
        List<Pet> sameAddressPetList = petRepository.findListByAddress(user.getSearchAddress());
        System.out.println("gender = " + gender);
        System.out.println("neutering = " + neutering);
        System.out.println("vaccination = " + vaccination);

        model.addAttribute("filterGender", gender);
        model.addAttribute("filterNeutering", neutering);
        model.addAttribute("filterVaccination", vaccination);
        model.addAttribute("petList", sameAddressPetList);

        return "searchs/puppysearchfilter";
    }

    @PostMapping("/filter/walker")
    public String searchFilterWalker(@RequestParam("gender") String gender, @RequestParam("exp") String exp,
                                     @RequestParam("license") String license, Principal principal, Model model) {
        User user = userRepository.findByMemberId(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("ID not found"));
        List<User> sameAddressUserList = userRepository.findListByAddress(user.getSearchAddress());

        model.addAttribute("filterGender", gender);
        model.addAttribute("filterLicense", license);
        model.addAttribute("filterExp", exp);
        model.addAttribute("userList", sameAddressUserList);

        return "searchs/walkersearchfilter";
    }

}
