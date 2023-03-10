use HieuSach;

insert into TaiKhoan (TaiKhoan, MatKhau) values('duy','duy12');
insert into TaiKhoan (TaiKhoan, MatKhau) values('nonn22','duy22');
GO

insert into KhachHang (HoTen, SoDienThoai, DiaChi, TaiKhoanID) values(N'duy', '0987654321', N'Thủ Đức', 1);

--select * from taikhoan;
insert into LoaiSanPham (TenLoai) values(N'Truyện');
insert into LoaiSanPham (TenLoai) values(N'Bút');

insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Kim đồng', N'Hà Nội', '0987654321');
insert into NhaCungCap (tenNCC, DiaChi, SoDienThoai) values(N'Thiên kim', N'Hà Nội', '0987654322');

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp) values(1, 1, N'Truyện conan', 15000);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp) values(1, 1, N'Truyện doraemon', 16000);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp) values(1, 1, N'Truyện Shin cậu bé bút chì', 14500);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp) values(1, 1, N'Truyện 7 viên ngọc rồng', 17000);

insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp) values(5, 5, N'Bút bi', 5000);
insert into SanPham (MaNCC, MaLoai, TenSp, GiaSp) values(5, 5, N'Bút chì', 6000);

INSERT INTO TaiKhoan (TaiKhoan, MatKhau)
VALUES ('nhanvien1', 'nv1');
INSERT INTO NhanVien (TaiKhoanID, TenNv, SoDienThoai, DiaChi, Email)
VALUES
(   3,   -- TaiKhoanID - int
    N'Thu Ngân 1', -- TenNv - nvarchar(50)
    '0987654321',  -- SoDienThoai - varchar(15)
    N'Quận 7, TP.HCM', -- DiaChi - nvarchar(255)
    'nhanvien1@gmail.com'  -- CaLamViec - int
    );

USE HieuSach
GO
SELECT *
FROM NhanVien nv
    JOIN TaiKhoan tk
        ON tk.ID = nv.TaiKhoanID
WHERE nv.TaiKhoanID = 1


INSERT INTO TaiKhoan (TaiKhoan, MatKhau, Role)
VALUES('lehathuong', 'lehathuong', 0)
DECLARE @taiKhoanID INT = SCOPE_IDENTITY();
INSERT INTO KhachHang (HoTen, DiaChi, TaiKhoanID, SoDienThoai)
VALUES
(   N'Lê Hà Thương', -- HoTen - nvarchar(50)
    N'Tân Phú, Hồ Chí Minh', -- DiaChi - nvarchar(255)
    @taiKhoanID,   -- TaiKhoanID - int
    N'0989667255'  -- SoDienThoai - nvarchar(15)
    )


	SELECT * FROM NhanVien
SELECT *
FROM HoaDon hd
    JOIN KhachHang kh
        ON hd.MaKH = kh.MaKH
    JOIN NhanVien nv
        ON nv.MaNv = hd.MaNV
WHERE hd.MaHD = 1;