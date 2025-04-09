CREATE TABLE QUYEN (
    maQuyen INT NOT NULL PRIMARY KEY,
    tenQuyen NVARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO QUYEN (maQuyen, tenQuyen) VALUES
(1, N'Quản trị viên (admin)'),
(2, N'Quản lý'),
(3, N'Nhân viên bán hàng'),
(4, N'Nhân viên kế toán'),
(5, N'Nhân viên bảo hành');

CREATE TABLE CHUCNANG (
    maCN INT NOT NULL PRIMARY KEY,
    tenCN NVARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO CHUCNANG (maCN, tenCN) VALUES
(1, N'Quản Lí Sản Phẩm'),
(2, N'Quản Lí Cấu Hình'),
(3, N'Quản Lí Khách Hàng'),
(4, N'Quản Lí Nhân Viên'),
(5, N'Quản Lí Tài Khoản'),
(6, N'Quản Lí Nhà Cung Cấp'),
(7, N'Quản Lí Nhập Hàng'),
(8, N'Quản Lí Xuất Hàng'),
(9, N'Quản Lí Phân Quyền'),
(10, N'Quẩn Lí Bảo Hành'),
(11, N'Quản Lí Thống Kê');

CREATE TABLE HANHDONG (
	maHD VARCHAR(10) PRIMARY KEY,
	tenHD NVARCHAR(30) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO HANHDONG (maHD, tenHD) VALUES
('view', N'XEM'), 
('add', N'THÊM'), 
('edit', N'SỬA'), 
('delete', N'XÓA');

CREATE TABLE CHITIETCHUCNANG (
    maCN INT NOT NULL,
    maQuyen INT NOT NULL,
	maHD VARCHAR(10) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    PRIMARY KEY (maCN, maQuyen, maHD),
    FOREIGN KEY (maCN) REFERENCES CHUCNANG(maCN),
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen),
	FOREIGN KEY (maHD) REFERENCES HANHDONG(maHD)
);
INSERT INTO CHITIETCHUCNANG(maCN, maQuyen, maHD) VALUES
(1, 1, 'view'), (1, 1, 'add'), (1, 1, 'edit'), (1, 1, 'delete'),
(2, 1, 'view'), (2, 1, 'add'), (2, 1, 'edit'), (2, 1, 'delete'),
(3, 1, 'view'), (3, 1, 'add'), (3, 1, 'edit'), (3, 1, 'delete'),
(4, 1, 'view'), (4, 1, 'add'), (4, 1, 'edit'), (4, 1, 'delete'),
(5, 1, 'view'), (5, 1, 'add'), (5, 1, 'edit'), (5, 1, 'delete'),
(6, 1, 'view'), (6, 1, 'add'), (6, 1, 'edit'), (6, 1, 'delete'),
(7, 1, 'view'), (7, 1, 'add'), (7, 1, 'edit'), (7, 1, 'delete'),
(8, 1, 'view'), (8, 1, 'add'), (8, 1, 'edit'), (8, 1, 'delete'),
(9, 1, 'view'), (9, 1, 'add'), (9, 1, 'edit'), (9, 1, 'delete'),
(10, 1, 'view'), (10, 1, 'add'), (10, 1, 'edit'), (10, 1, 'delete'),
(11, 1, 'view'), (11, 1, 'add'), (11, 1, 'edit'), (11, 1, 'delete');

CREATE TABLE NHANVIEN (
    maNV INT NOT NULL PRIMARY KEY,
    tenNV NVARCHAR(50) NOT NULL,
    sdt VARCHAR(11),
    email VARCHAR(100),
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO NHANVIEN (maNV, tenNV, sdt, email) VALUES
(1, N'Phạm Đình Duy Thái', '0912345678', 'dthai@gmail.com'),
(2, N'Lê Văn Nhất', '0923456789', 'nhat@gmail.com'),
(3, N'Mai Thành Trung', '0934567890', 'trung@gmail.com'),
(4, N'Hồ Minh TIến', '0945678901', 'tien@gmail.com'),
(5, N'Đặng Thái Tú', '0956789012', 'tututu@gmail.com');


CREATE TABLE TAIKHOAN (
    tenDangNhap NVARCHAR(50) NOT NULL PRIMARY KEY,
    matKhau VARCHAR(50) NOT NULL,
    maQuyen INT NOT NULL,
    maNV INT NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maQuyen) REFERENCES QUYEN(maQuyen),
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV)
);
INSERT INTO TAIKHOAN (tenDangNhap, matKhau, maQuyen, maNV) VALUES
('admin1', '123456', 1, 1),
('banhang1', '123456', 3, 2),
('banhang2', '123456', 3, 3),
('banhang3', '123456', 3, 4),
('ketoan1', '123456', 4, 5);


CREATE TABLE KHACHHANG (
    maKH INT NOT NULL PRIMARY KEY,
    tenKH NVARCHAR(50) NOT NULL,
    sdt VARCHAR(11),
    email VARCHAR(100),
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO KHACHHANG (maKH, tenKH, sdt, email) VALUES
(1, N'Nguyễn Văn An', '0911000001', 'annguyen@gmail.com'),
(2, N'Lê Thị Bích', '0911000002', 'bichle@gmail.com'),
(3, N'Trần Hoàng Nam', '0911000003', 'namtran@gmail.com'),
(4, N'Phạm Minh Khoa', '0911000004', 'khoapham@gmail.com'),
(5, N'Vũ Thị Hồng', '0911000005', 'hongvu@gmail.com'),
(6, N'Đặng Quốc Bảo', '0911000006', 'baodang@gmail.com'),
(7, N'Hồ Văn Tú', '0911000007', 'tuho@gmail.com'),
(8, N'Bùi Ngọc Lan', '0911000008', 'lanbui@gmail.com'),
(9, N'Hoàng Anh Dũng', '0911000009', 'dunghoang@gmail.com'),
(10, N'Ngô Thị Thanh', '0911000010', 'thanhngo@gmail.com'),
(11, N'Trịnh Công Sơn', '0911000011', 'sontrinh@gmail.com'),
(12, N'Nguyễn Thị Mai', '0911000012', 'mainuyen@gmail.com'),
(13, N'Phan Văn Hùng', '0911000013', 'hungphan@gmail.com'),
(14, N'Lý Kim Chi', '0911000014', 'chily@gmail.com'),
(15, N'Đỗ Quang Khải', '0911000015', 'khaido@gmail.com'),
(16, N'Tống Thị Yến', '0911000016', 'yentong@gmail.com'),
(17, N'Cao Minh Tâm', '0911000017', 'tamcao@gmail.com'),
(18, N'Nguyễn Hữu Tài', '0911000018', 'tainh@gmail.com'),
(19, N'Lâm Ngọc Diễm', '0911000019', 'diemlam@gmail.com'),
(20, N'Trương Quốc Vinh', '0911000020', 'vinhtruong@gmail.com');


CREATE TABLE NHACUNGCAP (
    maNCC INT NOT NULL PRIMARY KEY,
    tenNCC NVARCHAR(100) NOT NULL,
    diaChi NVARCHAR(100) NOT NULL,
    sdt VARCHAR(11) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO NHACUNGCAP (maNCC, tenNCC, diaChi, sdt) VALUES
(1, N'Công ty TNHH Công Nghệ Việt', N'12 Nguyễn Văn Bảo, Gò Vấp, TP.HCM', '0912345678'),
(2, N'Công ty Cổ phần Phát Triển Tin Học', N'45 Lý Tự Trọng, Quận 1, TP.HCM', '0923456789'),
(3, N'Công ty TNHH Linh Kiện Máy Tính', N'89 Trường Chinh, Tân Bình, TP.HCM', '0934567890'),
(4, N'Công ty Cổ phần Máy Tính Toàn Cầu', N'56 Nguyễn Huệ, Quận 1, TP.HCM', '0945678901'),
(5, N'Công ty TNHH Thương Mại Điện Tử', N'101 Cách Mạng Tháng 8, Quận 10, TP.HCM', '0956789012');


CREATE TABLE PHANLOAI (
    maLoai INT NOT NULL PRIMARY KEY,
    tenLoai NVARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO PHANLOAI (maLoai, tenLoai) VALUES
(1, N'Laptop Văn Phòng - Học Tập'),
(2, N'Laptop Gaming'),
(3, N'Laptop Đồ Họa - Kỹ Thuật');


CREATE TABLE THUONGHIEU (
    maTH INT NOT NULL PRIMARY KEY,
    tenTH NVARCHAR(100) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO THUONGHIEU (maTH, tenTH) VALUES
(1, N'Dell'),
(2, N'HP'),
(3, N'Asus'),
(4, N'Acer'),
(5, N'Lenovo'),
(6, N'MSI');


CREATE TABLE DOPHANGIAI (
    maDPG INT NOT NULL PRIMARY KEY,
    tenDPG VARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO DOPHANGIAI (maDPG, tenDPG) VALUES
(1, 'HD'),
(2, 'Full HD'),
(3, '2K (Quad HD)'),
(4, '3K'),
(5, 'WUXGA'),
(6, 'WQXGA'),
(7, 'WQUXGA'),
(8, '4K (Ultra HD)');


CREATE TABLE CPU (
    maCPU INT NOT NULL PRIMARY KEY,
    tenCPU VARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO CPU (maCPU, tenCPU) VALUES
(1, 'Intel Core i3'),
(2, 'Intel Core i5'),
(3, 'Intel Core i7'),
(4, 'Intel Core i9'),
(5, 'AMD Ryzen 5'),
(6, 'AMD Ryzen 7'),
(7, 'AMD Ryzen 9'),
(8, 'Qualcomm Snapdragon'),
(9, 'Snapdragon X Plus');


CREATE TABLE RAM (
    maRAM INT NOT NULL PRIMARY KEY,
    dungLuongRAM VARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO RAM (maRAM, dungLuongRAM) VALUES
(1, '4GB'),
(2, '8GB'),
(3, '12GB'),
(4, '16GB'),
(5, '18GB'),
(6, '24GB'),
(7, '32GB'),
(8, '36GB'),
(9, '48GB'),
(10, '64GB');


CREATE TABLE ROM (
    maROM INT NOT NULL PRIMARY KEY,
    dungLuongROM VARCHAR(50) NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0
);
INSERT INTO ROM (maROM, dungLuongROM) VALUES
(1, '128GB'),
(2, '256GB'),
(3, '512GB'),
(4, '1TB'),
(5, '2TB'),
(6, '3TB'),
(7, '4TB'),
(8, '6TB');


CREATE TABLE SANPHAM (
    maSP INT NOT NULL PRIMARY KEY,
    tenSP VARCHAR(50) NOT NULL,
    giaSP INT NOT NULL,
    soLuongTon INT NOT NULL,
    maCPU INT NOT NULL,
    maRAM INT NOT NULL,
    maROM INT NOT NULL,
    maTH INT NOT NULL,
    maDPG INT NOT NULL,
    maLoai INT NOT NULL,
    thoigianBH INT NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maCPU) REFERENCES CPU(maCPU),
    FOREIGN KEY (maRAM) REFERENCES RAM(maRAM),
    FOREIGN KEY (maROM) REFERENCES ROM(maROM),
    FOREIGN KEY (maTH) REFERENCES THUONGHIEU(maTH),
    FOREIGN KEY (maDPG) REFERENCES DOPHANGIAI(maDPG),
    FOREIGN KEY (maLoai) REFERENCES PHANLOAI(maLoai)
);
INSERT INTO SANPHAM (maSP, tenSP, giaSP, soLuongTon, maCPU, maRAM, maROM, maTH, maDPG, maLoai, thoigianBH) VALUES
(1, N'Dell Inspiron 15', 15000000, 0, 2, 2, 2, 1, 2, 1, 24),
(2, N'Dell XPS 13', 32000000, 0, 4, 4, 3, 1, 5, 1, 36),
(3, N'Dell Latitude 7420', 28000000, 0, 3, 3, 2, 1, 2, 1, 24),
(4, N'HP Pavilion 14', 14000000, 0, 2, 2, 2, 2, 2, 1, 24),
(5, N'HP Envy x360', 26000000, 0, 3, 4, 3, 2, 3, 1, 36),
(6, N'HP Omen 16', 37000000, 0, 4, 5, 4, 2, 8, 2, 24),
(7, N'Asus ZenBook 14', 18000000, 0, 2, 3, 2, 3, 2, 1, 24),
(8, N'Asus ROG Zephyrus G14', 42000000, 8, 6, 6, 4, 3, 8, 2, 36),
(9, N'Asus TUF Gaming F15', 25000000, 0, 5, 4, 3, 3, 2, 2, 24),
(10, N'Acer Aspire 5', 13000000, 0, 1, 2, 2, 4, 2, 1, 24),
(11, N'Acer Predator Helios 300', 35000000, 0, 4, 5, 4, 4, 8, 2, 36),
(12, N'Acer Swift 3', 16000000, 0, 3, 3, 2, 4, 3, 1, 24),
(13, N'Lenovo IdeaPad 5', 14500000, 0, 2, 3, 2, 5, 2, 1, 24),
(14, N'Lenovo ThinkPad X1 Carbon', 33000000, 0, 3, 5, 3, 5, 5, 1, 36),
(15, N'Lenovo Legion 5 Pro', 40000000, 0, 6, 6, 4, 5, 8, 2, 24),
(16, N'MSI Modern 14', 17000000, 0, 2, 3, 2, 6, 2, 1, 24),
(17, N'MSI Stealth 15M', 39000000, 0, 4, 6, 4, 6, 8, 2, 36),
(18, N'MSI GF63 Thin', 23000000, 0, 3, 4, 3, 6, 2, 2, 24),
(19, N'Asus VivoBook 15', 13500000, 0, 1, 2, 1, 3, 2, 1, 24),
(20, N'Dell G15', 28000000, 0, 5, 5, 4, 1, 8, 2, 36);


CREATE TABLE PHIEUNHAP (
    maPN INT NOT NULL PRIMARY KEY,
    maNV INT NOT NULL,
    maNCC INT NOT NULL,
    tongTien INT NOT NULL,
    ngayNhap DATE NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maNCC) REFERENCES NHACUNGCAP(maNCC)
);

CREATE TABLE CHITIETPHIEUNHAP (
    maSP INT NOT NULL,
    maPN INT NOT NULL,
    soLuongSP INT NOT NULL,
    giaNhap INT NOT NULL,
    PRIMARY KEY (maSP, maPN),
    FOREIGN KEY (maSP) REFERENCES SANPHAM(maSP),
    FOREIGN KEY (maPN) REFERENCES PHIEUNHAP(maPN)
);

CREATE TABLE PHIEUXUAT (
    maPX INT NOT NULL PRIMARY KEY,
    maNV INT NOT NULL,
    maKH INT NULL,
    tongTien INT NOT NULL,
    ngayXuat DATE NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maNV) REFERENCES NHANVIEN(maNV),
    FOREIGN KEY (maKH) REFERENCES KHACHHANG(maKH)
);

CREATE TABLE CHITIETPHIEUXUAT (
    maSP INT NOT NULL,
    maPX INT NOT NULL,
    giaBan INT NOT NULL,
    soLuongSP INT NOT NULL,
    serialSP VARCHAR(50) NOT NULL,
    PRIMARY KEY (maSP, maPX, serialSP),
    FOREIGN KEY (maSP) REFERENCES SANPHAM(maSP),
    FOREIGN KEY (maPX) REFERENCES PHIEUXUAT(maPX)
);

CREATE TABLE PHIEUBAOHANH (
    maPBH INT NOT NULL PRIMARY KEY,
    maSP INT NOT NULL,
    maPX INT NULL,
    ngayTiepNhan DATE NOT NULL,
    moTaLoi NVARCHAR(100) NOT NULL,
    trangThaiBH INT NOT NULL,
    maNVBH INT NOT NULL,
	trangThaiXoa INT NOT NULL DEFAULT 0,
    FOREIGN KEY (maSP) REFERENCES SANPHAM(maSP),
    FOREIGN KEY (maPX) REFERENCES PHIEUXUAT(maPX),
    FOREIGN KEY (maNVBH) REFERENCES NHANVIEN(maNV)
);
