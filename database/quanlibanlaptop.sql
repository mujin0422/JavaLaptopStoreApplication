-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 08, 2025 lúc 08:48 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlibanlaptop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietchucnang`
--

CREATE TABLE `chitietchucnang` (
  `maCN` int(11) NOT NULL,
  `maQuyen` int(11) NOT NULL,
  `maHD` varchar(10) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietchucnang`
--

INSERT INTO `chitietchucnang` (`maCN`, `maQuyen`, `maHD`, `trangThaiXoa`) VALUES
(1, 1, 'add', 0),
(1, 1, 'delete', 0),
(1, 1, 'edit', 0),
(1, 1, 'view', 0),
(2, 1, 'add', 0),
(2, 1, 'delete', 0),
(2, 1, 'edit', 0),
(2, 1, 'view', 0),
(3, 1, 'add', 0),
(3, 1, 'delete', 0),
(3, 1, 'edit', 0),
(3, 1, 'view', 0),
(4, 1, 'add', 0),
(4, 1, 'delete', 0),
(4, 1, 'edit', 0),
(4, 1, 'view', 0),
(5, 1, 'add', 0),
(5, 1, 'delete', 0),
(5, 1, 'edit', 0),
(5, 1, 'view', 0),
(6, 1, 'add', 0),
(6, 1, 'delete', 0),
(6, 1, 'edit', 0),
(6, 1, 'view', 0),
(7, 1, 'add', 0),
(7, 1, 'delete', 0),
(7, 1, 'edit', 0),
(7, 1, 'view', 0),
(8, 1, 'add', 0),
(8, 1, 'delete', 0),
(8, 1, 'edit', 0),
(8, 1, 'view', 0),
(9, 1, 'add', 0),
(9, 1, 'delete', 0),
(9, 1, 'edit', 0),
(9, 1, 'view', 0),
(10, 1, 'add', 0),
(10, 1, 'delete', 0),
(10, 1, 'edit', 0),
(10, 1, 'view', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `maSP` int(11) NOT NULL,
  `maPN` int(11) NOT NULL,
  `soLuongSP` int(11) NOT NULL,
  `giaNhap` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieuxuat`
--

CREATE TABLE `chitietphieuxuat` (
  `maSP` int(11) NOT NULL,
  `maPX` int(11) NOT NULL,
  `giaBan` int(11) NOT NULL,
  `soLuongSP` int(11) NOT NULL,
  `serialSP` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucnang`
--

CREATE TABLE `chucnang` (
  `maCN` int(11) NOT NULL,
  `tenCN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chucnang`
--

INSERT INTO `chucnang` (`maCN`, `tenCN`, `trangThaiXoa`) VALUES
(1, 'Quản Lí Sản Phẩm', 0),
(2, 'Quản Lí Cấu Hình', 0),
(3, 'Quản Lí Khách Hàng', 0),
(4, 'Quản Lí Nhân Viên', 0),
(5, 'Quản Lí Tài Khoản', 0),
(6, 'Quản Lí Nhà Cung Cấp', 0),
(7, 'Quản Lí Nhập Hàng', 0),
(8, 'Quản Lí Xuất Hàng', 0),
(9, 'Quản Lí Phân Quyền', 0),
(10, 'Quản Lí Thống Kê', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cpu`
--

CREATE TABLE `cpu` (
  `maCPU` int(11) NOT NULL,
  `tenCPU` varchar(50) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cpu`
--

INSERT INTO `cpu` (`maCPU`, `tenCPU`, `trangThaiXoa`) VALUES
(1, 'Intel Core i3', 0),
(2, 'Intel Core i5', 0),
(3, 'Intel Core i7', 0),
(4, 'Intel Core i9', 0),
(5, 'AMD Ryzen 5', 0),
(6, 'AMD Ryzen 7', 0),
(7, 'AMD Ryzen 9', 0),
(8, 'Qualcomm Snapdragon', 0),
(9, 'Snapdragon X Plus', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dophangiai`
--

CREATE TABLE `dophangiai` (
  `maDPG` int(11) NOT NULL,
  `tenDPG` varchar(50) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dophangiai`
--

INSERT INTO `dophangiai` (`maDPG`, `tenDPG`, `trangThaiXoa`) VALUES
(1, 'HD', 0),
(2, 'Full HD', 0),
(3, '2K (Quad HD)', 0),
(4, '3K', 0),
(5, 'WUXGA', 0),
(6, 'WQXGA', 0),
(7, 'WQUXGA', 0),
(8, '4K (Ultra HD)', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hanhdong`
--

CREATE TABLE `hanhdong` (
  `maHD` varchar(10) NOT NULL,
  `tenHD` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hanhdong`
--

INSERT INTO `hanhdong` (`maHD`, `tenHD`, `trangThaiXoa`) VALUES
('add', 'THÊM', 0),
('delete', 'XÓA', 0),
('edit', 'SỬA', 0),
('view', 'XEM', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `maKH` int(11) NOT NULL,
  `tenKH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdt` varchar(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`maKH`, `tenKH`, `sdt`, `email`, `trangThaiXoa`) VALUES
(1, 'Nguyễn Văn An', '0911000001', 'an.nguyen@gmail.com', 0),
(2, 'Lê Thị Bích', '0911000002', 'bich.le@gmail.com', 0),
(3, 'Trần Hoàng Nam', '0911000003', 'nam.tran@gmail.com', 0),
(4, 'Phạm Minh Khoa', '0911000004', 'khoa.pham@gmail.com', 0),
(5, 'Vũ Thị Hồng', '0911000005', 'hong.vu@gmail.com', 0),
(6, 'Đặng Quốc Bảo', '0911000006', 'bao.dang@gmail.com', 0),
(7, 'Hồ Văn Tú', '0911000007', 'tu.ho@gmail.com', 0),
(8, 'Bùi Ngọc Lan', '0911000008', 'lan.bui@gmail.com', 0),
(9, 'Hoàng Anh Dũng', '0911000009', 'dung.hoang@gmail.com', 0),
(10, 'Ngô Thị Thanh', '0911000010', 'thanh.ngo@gmail.com', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `maNCC` int(11) NOT NULL,
  `tenNCC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `diaChi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdt` varchar(11) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`maNCC`, `tenNCC`, `diaChi`, `sdt`, `trangThaiXoa`) VALUES
(1, 'Công ty TNHH Công Nghệ Việt', '12 Nguyễn Văn Bảo, Gò Vấp, TP.HCM', '0912345678', 0),
(2, 'Công ty Cổ phần Phát Triển Tin Học', '45 Lý Tự Trọng, Quận 1, TP.HCM', '0923456789', 0),
(3, 'Công ty TNHH Linh Kiện Máy Tính', '89 Trường Chinh, Tân Bình, TP.HCM', '0934567890', 0),
(4, 'Công ty Cổ phần Máy Tính Toàn Cầu', '56 Nguyễn Huệ, Quận 1, TP.HCM', '0945678901', 0),
(5, 'Công ty TNHH Thương Mại Điện Tử', '101 Cách Mạng Tháng 8, Quận 10, TP.HCM', '0956789012', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `maNV` int(11) NOT NULL,
  `tenNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdt` varchar(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`maNV`, `tenNV`, `sdt`, `email`, `trangThaiXoa`) VALUES
(1, 'Phạm Đình Duy Thái', '0912345678', 'dthai@gmail.com', 0),
(2, 'Lê Văn Nhất', '0923456789', 'nhat@gmail.com', 0),
(3, 'Mai Thành Trung', '0934567890', 'trung@gmail.com', 0),
(4, 'Hồ Minh TIến', '0945678901', 'tien@gmail.com', 0),
(5, 'Đặng Thái Tú', '0956789012', 'tututu@gmail.com', 0),
(6, 'uytre', '0987654321', 'fhsfu@gmail.com', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanloai`
--

CREATE TABLE `phanloai` (
  `maLoai` int(11) NOT NULL,
  `tenLoai` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phanloai`
--

INSERT INTO `phanloai` (`maLoai`, `tenLoai`, `trangThaiXoa`) VALUES
(1, 'Laptop Văn Phòng - Học Tập', 0),
(2, 'Laptop Gaming', 0),
(3, 'Laptop Đồ Họa - Kỹ Thuật', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieubaohanh`
--

CREATE TABLE `phieubaohanh` (
  `maPBH` int(11) NOT NULL,
  `maSP` int(11) NOT NULL,
  `maPX` int(11) DEFAULT NULL,
  `ngayTiepNhan` date NOT NULL,
  `moTaLoi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangThaiBH` int(11) NOT NULL,
  `maNVBH` int(11) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maPN` int(11) NOT NULL,
  `maNV` int(11) NOT NULL,
  `maNCC` int(11) NOT NULL,
  `tongTien` int(11) NOT NULL,
  `ngayNhap` date NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuxuat`
--

CREATE TABLE `phieuxuat` (
  `maPX` int(11) NOT NULL,
  `maNV` int(11) NOT NULL,
  `maKH` int(11) DEFAULT NULL,
  `tongTien` int(11) NOT NULL,
  `ngayXuat` date NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `quyen`
--

CREATE TABLE `quyen` (
  `maQuyen` int(11) NOT NULL,
  `tenQuyen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `quyen`
--

INSERT INTO `quyen` (`maQuyen`, `tenQuyen`, `trangThaiXoa`) VALUES
(1, 'Quản trị viên (admin)', 0),
(2, 'Nhân viên bán hàng', 0),
(3, 'Kế toán', 0),
(4, 'Quản lý', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ram`
--

CREATE TABLE `ram` (
  `maRAM` int(11) NOT NULL,
  `dungLuongRAM` varchar(50) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ram`
--

INSERT INTO `ram` (`maRAM`, `dungLuongRAM`, `trangThaiXoa`) VALUES
(1, '4GB', 0),
(2, '8GB', 0),
(3, '12GB', 0),
(4, '16GB', 0),
(5, '18GB', 0),
(6, '24GB', 0),
(7, '32GB', 0),
(8, '36GB', 0),
(9, '48GB', 0),
(10, '64GB', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rom`
--

CREATE TABLE `rom` (
  `maROM` int(11) NOT NULL,
  `dungLuongROM` varchar(50) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `rom`
--

INSERT INTO `rom` (`maROM`, `dungLuongROM`, `trangThaiXoa`) VALUES
(1, '128GB', 0),
(2, '256GB', 0),
(3, '512GB', 0),
(4, '1TB', 0),
(5, '2TB', 0),
(6, '3TB', 0),
(7, '4TB', 0),
(8, '6TB', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `maSP` int(11) NOT NULL,
  `tenSP` varchar(50) NOT NULL,
  `giaSP` int(11) NOT NULL,
  `soLuongTon` int(11) NOT NULL,
  `maCPU` int(11) NOT NULL,
  `maRAM` int(11) NOT NULL,
  `maROM` int(11) NOT NULL,
  `maTH` int(11) NOT NULL,
  `maDPG` int(11) NOT NULL,
  `maLoai` int(11) NOT NULL,
  `thoigianBH` int(11) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`maSP`, `tenSP`, `giaSP`, `soLuongTon`, `maCPU`, `maRAM`, `maROM`, `maTH`, `maDPG`, `maLoai`, `thoigianBH`, `trangThaiXoa`) VALUES
(1, 'Dell Inspiron 15', 15000000, 0, 2, 2, 2, 1, 2, 1, 24, 0),
(2, 'Dell XPS 13', 32000000, 0, 4, 4, 3, 1, 5, 1, 36, 0),
(3, 'Dell Latitude 7420', 28000000, 0, 3, 3, 2, 1, 2, 1, 24, 0),
(4, 'HP Pavilion 14', 14000000, 0, 2, 2, 2, 2, 2, 1, 24, 0),
(5, 'HP Envy x360', 26000000, 0, 3, 4, 3, 2, 3, 1, 36, 0),
(6, 'HP Omen 16', 37000000, 0, 4, 5, 4, 2, 8, 2, 24, 0),
(7, 'Asus ZenBook 14', 18000000, 0, 2, 3, 2, 3, 2, 1, 24, 0),
(8, 'Asus ROG Zephyrus G14', 42000000, 8, 6, 6, 4, 3, 8, 2, 36, 0),
(9, 'Asus TUF Gaming F15', 25000000, 0, 5, 4, 3, 3, 2, 2, 24, 0),
(10, 'Acer Aspire 5', 13000000, 0, 1, 2, 2, 4, 2, 1, 24, 0),
(11, 'Acer Predator Helios 300', 35000000, 0, 4, 5, 4, 4, 8, 2, 36, 0),
(12, 'Acer Swift 3', 16000000, 0, 3, 3, 2, 4, 3, 1, 24, 0),
(13, 'Lenovo IdeaPad 5', 14500000, 0, 2, 3, 2, 5, 2, 1, 24, 0),
(14, 'Lenovo ThinkPad X1 Carbon', 33000000, 0, 3, 5, 3, 5, 5, 1, 36, 0),
(15, 'Lenovo Legion 5 Pro', 40000000, 0, 6, 6, 4, 5, 8, 2, 24, 0),
(16, 'MSI Modern 14', 17000000, 0, 2, 3, 2, 6, 2, 1, 24, 0),
(17, 'MSI Stealth 15M', 39000000, 0, 4, 6, 4, 6, 8, 2, 36, 0),
(18, 'MSI GF63 Thin', 23000000, 0, 3, 4, 3, 6, 2, 2, 24, 0),
(19, 'Asus VivoBook 15', 13500000, 0, 1, 2, 1, 3, 2, 1, 24, 0),
(20, 'Dell G15', 28000000, 0, 5, 5, 4, 1, 8, 2, 36, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `tenDangNhap` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `matKhau` varchar(50) NOT NULL,
  `maQuyen` int(11) NOT NULL,
  `maNV` int(11) NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`tenDangNhap`, `matKhau`, `maQuyen`, `maNV`, `trangThaiXoa`) VALUES
('admin1', '123456', 1, 1, 0),
('banhang1', '123456', 2, 2, 0),
('banhang2', '123456', 2, 3, 0),
('banhang3', '123456', 2, 4, 0),
('ketoan1', '123456', 3, 5, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuonghieu`
--

CREATE TABLE `thuonghieu` (
  `maTH` int(11) NOT NULL,
  `tenTH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trangThaiXoa` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thuonghieu`
--

INSERT INTO `thuonghieu` (`maTH`, `tenTH`, `trangThaiXoa`) VALUES
(1, 'Dell', 0),
(2, 'HP', 0),
(3, 'Asus', 0),
(4, 'Acer', 0),
(5, 'Lenovo', 0),
(6, 'MSI', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietchucnang`
--
ALTER TABLE `chitietchucnang`
  ADD PRIMARY KEY (`maCN`,`maQuyen`,`maHD`),
  ADD KEY `maQuyen` (`maQuyen`),
  ADD KEY `maHD` (`maHD`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`maSP`,`maPN`),
  ADD KEY `maPN` (`maPN`);

--
-- Chỉ mục cho bảng `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  ADD PRIMARY KEY (`maSP`,`maPX`),
  ADD KEY `maPX` (`maPX`);

--
-- Chỉ mục cho bảng `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`maCN`);

--
-- Chỉ mục cho bảng `cpu`
--
ALTER TABLE `cpu`
  ADD PRIMARY KEY (`maCPU`);

--
-- Chỉ mục cho bảng `dophangiai`
--
ALTER TABLE `dophangiai`
  ADD PRIMARY KEY (`maDPG`);

--
-- Chỉ mục cho bảng `hanhdong`
--
ALTER TABLE `hanhdong`
  ADD PRIMARY KEY (`maHD`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`maKH`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`maNCC`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`maNV`);

--
-- Chỉ mục cho bảng `phanloai`
--
ALTER TABLE `phanloai`
  ADD PRIMARY KEY (`maLoai`);

--
-- Chỉ mục cho bảng `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  ADD PRIMARY KEY (`maPBH`),
  ADD KEY `maSP` (`maSP`),
  ADD KEY `maPX` (`maPX`),
  ADD KEY `maNVBH` (`maNVBH`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maPN`),
  ADD KEY `maNV` (`maNV`),
  ADD KEY `maNCC` (`maNCC`);

--
-- Chỉ mục cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD PRIMARY KEY (`maPX`),
  ADD KEY `maNV` (`maNV`),
  ADD KEY `maKH` (`maKH`);

--
-- Chỉ mục cho bảng `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`maQuyen`);

--
-- Chỉ mục cho bảng `ram`
--
ALTER TABLE `ram`
  ADD PRIMARY KEY (`maRAM`);

--
-- Chỉ mục cho bảng `rom`
--
ALTER TABLE `rom`
  ADD PRIMARY KEY (`maROM`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`maSP`),
  ADD KEY `maCPU` (`maCPU`),
  ADD KEY `maRAM` (`maRAM`),
  ADD KEY `maROM` (`maROM`),
  ADD KEY `maTH` (`maTH`),
  ADD KEY `maDPG` (`maDPG`),
  ADD KEY `maLoai` (`maLoai`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`tenDangNhap`),
  ADD KEY `maQuyen` (`maQuyen`),
  ADD KEY `maNV` (`maNV`);

--
-- Chỉ mục cho bảng `thuonghieu`
--
ALTER TABLE `thuonghieu`
  ADD PRIMARY KEY (`maTH`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietchucnang`
--
ALTER TABLE `chitietchucnang`
  ADD CONSTRAINT `chitietchucnang_ibfk_1` FOREIGN KEY (`maCN`) REFERENCES `chucnang` (`maCN`),
  ADD CONSTRAINT `chitietchucnang_ibfk_2` FOREIGN KEY (`maQuyen`) REFERENCES `quyen` (`maQuyen`),
  ADD CONSTRAINT `chitietchucnang_ibfk_3` FOREIGN KEY (`maHD`) REFERENCES `hanhdong` (`maHD`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`),
  ADD CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`maPN`) REFERENCES `phieunhap` (`maPN`);

--
-- Các ràng buộc cho bảng `chitietphieuxuat`
--
ALTER TABLE `chitietphieuxuat`
  ADD CONSTRAINT `chitietphieuxuat_ibfk_1` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`),
  ADD CONSTRAINT `chitietphieuxuat_ibfk_2` FOREIGN KEY (`maPX`) REFERENCES `phieuxuat` (`maPX`);

--
-- Các ràng buộc cho bảng `phieubaohanh`
--
ALTER TABLE `phieubaohanh`
  ADD CONSTRAINT `phieubaohanh_ibfk_1` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`),
  ADD CONSTRAINT `phieubaohanh_ibfk_2` FOREIGN KEY (`maPX`) REFERENCES `phieuxuat` (`maPX`),
  ADD CONSTRAINT `phieubaohanh_ibfk_3` FOREIGN KEY (`maNVBH`) REFERENCES `nhanvien` (`maNV`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`maNV`),
  ADD CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`);

--
-- Các ràng buộc cho bảng `phieuxuat`
--
ALTER TABLE `phieuxuat`
  ADD CONSTRAINT `phieuxuat_ibfk_1` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`maNV`),
  ADD CONSTRAINT `phieuxuat_ibfk_2` FOREIGN KEY (`maKH`) REFERENCES `khachhang` (`maKH`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`maCPU`) REFERENCES `cpu` (`maCPU`),
  ADD CONSTRAINT `sanpham_ibfk_2` FOREIGN KEY (`maRAM`) REFERENCES `ram` (`maRAM`),
  ADD CONSTRAINT `sanpham_ibfk_3` FOREIGN KEY (`maROM`) REFERENCES `rom` (`maROM`),
  ADD CONSTRAINT `sanpham_ibfk_4` FOREIGN KEY (`maTH`) REFERENCES `thuonghieu` (`maTH`),
  ADD CONSTRAINT `sanpham_ibfk_5` FOREIGN KEY (`maDPG`) REFERENCES `dophangiai` (`maDPG`),
  ADD CONSTRAINT `sanpham_ibfk_6` FOREIGN KEY (`maLoai`) REFERENCES `phanloai` (`maLoai`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `taikhoan_ibfk_1` FOREIGN KEY (`maQuyen`) REFERENCES `quyen` (`maQuyen`),
  ADD CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`maNV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
