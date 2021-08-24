package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.ProfileDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.*;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import com.bootcampProject.BootcampProject.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class SellerService extends BaseService {

    @Autowired
    private HeadersUtil headersUtil;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageTableRepository imageTableRepository;




}
