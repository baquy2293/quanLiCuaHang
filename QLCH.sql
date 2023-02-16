Create database QLCH;
Go
use QLCH;
GO

Create table SANPHAM(
	MaSP char(8) primary key Constraint Check_MaSP Check (MaSP Like 'SP[0-9][0-9][0-9][0-9][0-9][0-9]'),
	TenSP nvarchar(50) not null,
	LoaiSP nvarchar(20) not null,
	DonGia float not null,
	SoLuong int default 0 check(SoLuong >= 0),
	HinhAnh image,
	TrangThai nvarchar(20) not null check (TrangThai = N'Đang Bán' or TrangThai=N'Chưa Bán'),
	MoTa nvarchar(200)
)

Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000001', N'Snack Tôm Miếng Kirei', N'Đồ Ăn', 5000, 100, N'Đang Bán', N'Snack ăn liền ngon, giòn, rẻ.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000002', N' TH True Water 350ml', N'Đồ Ăn', 8000, 200, N'Đang Bán', N' Đạt tiêu chuẩn quốc tế, thiết kế bao bì nhỏ gọn, bạn dễ dàng tận hưởng trực tiếp mọi lúc mọi nơi.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000003', N'Doraemon Truyện Ngắn - Tập 32', N'Sách, Truyện', 18000, 100, N'Đang Bán', N'Những câu chuyện về chú mèo máy thông minh Doraemon và nhóm bạn Nobita, Shizuka, Suneo, Jaian, Dekisugi.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000004', N'Khẩu Trang Ngăn Vi Khuẩn Unicharm', N'Y Tế', 2000, 200, N'Đang Bán', N'Sản phẩm sở hữu thiết kế 3D ôm vừa vặn đường cong khuôn mặt, mang lại cảm giác thoải mái, dễ chịu.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000005', N'Iphone 14 Pro Max 512GB', N'Điện Thoại', 31990000, 100, N'Đang Bán', N'iPhone 14 Plus 512GB được ép chặt bởi khung viền kim loại mang đến trải nghiệm sử dụng chắc chắn, liền lạc và có tính thẩm mỹ cao.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000006', N'Laptop Asus ROG Zephyrus G15 GA503RM-LN006W', N'Máy Tính', 42690000, 60, N'Đang Bán', N'Asus ROG Zephyrus G15 Cấu Hình Cực Khủng - Màn hình 2k, 100% sRGB - Chiến Game, Đồ Họa Cực Đỉnh')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000007', N'Áo sơ mi nam', N'Quần Áo', 159000, 142, N'Đang Bán', N'Áo sơ mi nam form rộng cổ 2 ve LAHstore, thời trang trẻ, phong cách Hàn Quốc.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000008', N'Giầy da có gót', N'Giầy, Dép', 890000, 89, N'Đang Bán', N'Thiết kế độc quyền, cho cảm giác vô cùng êm ái khi mang ,tăng chiều cao vượt trội lên tới 10cm.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000009', N'ONE PIECE - TẬP 100', N'Sách, Truyện', 19800, 100, N'Đang Bán', N'Nhóm Luffy đối đầu với Kaido & Big Mom, trước liên minh mạnh nhất thế giới, liệu có phép màu nào giúp bọn họ giành chiến thắng hay không!?.')
Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, TrangThai, MoTa) 
Values ('SP000010', N'Ghế quỳ plywood, knee Chair', N'Đồ Gia Dụng', 1390000, 26, N'Đang Bán', N'Ghế Công Thái Học giúp ngồi thẳng lưng tránh được các bệnh văn phòng do ngồi nhiều làm việc.')


Create table KHACHHANG(
	MaKH char(10) primary key,
	TaiKhoan varchar(20) not null,
	TenKH nvarchar(30) not null,
	NgaySinh Date,
	DiaChi nvarchar(200),
	Sdt char(10) not null check(Sdt like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(30) check (Email like '%_@__%.__%' or Email like '%_@__%.__%.__%'),
	HinhAnh image,
	Diem int default 0 check(Diem >= 0)
)
Insert Into KHACHHANG(MaKH, TaiKhoan, TenKH, NgaySinh, DiaChi, Sdt, Email, HinhAnh, Diem)
Values 
	('KH00000001', 'ductra',N'Nguyễn Đức Trà','2002-3-6', N'Hải Dương', '0368547812', 'tradeptrai@gmail.com', null, 0 ),
	('KH00000002', 'hungthang123', N'Hoàng Hưng Thắng', '2002-10-4', N'Thanh Hóa', '0361563258', 'hungthangact@gmail.com', null, 0),
	('KH00000003', 'wibulanhat', N'Trần Tuấn Minh', '2002-8-20', N'Nam Định', '0364826139', 'gianhuemlawibu@gmail.com', null, 0),
	('KH00000004', 'caocuong2o', N'Phan Cao Cường', '2002-7-29', N'Hải Dương', '0341569256', 'caocuong2o@gmail.com', null, 0),
	('KH00000005', 'chuongluongthien', N'Phan Văn Chương', '2002-3-30', N'Nghệ An', '0367246133', 'dihoaviquy@gmail.com', null, 0),
	('KH00000006', 'thienthienxa', N'Trương Văn Thiện', '2002-2-25', N'Thanh Hóa', '0342469957', 'truongthien@gmail.com', null, 0),
	('KH00000007', 'thanhgaylon', N'Lê Thành', '2002-10-20', N'Thái Bình', '0822256697', 'lethanh@gmail.com', null, 0),
	('KH00000008', 'thinhdoitruong', N'Nguyễn Ngọc Thịnh', '2002-8-21', N'Nghệ An', '0374485635', 'thinhdeptrai@gmail.com', null, 0),
	('KH00000009', 'tiendoan', N'Đoàn Văn Tiến', '2002-1-25', N'Nam Định', '0347962544', 'tiendoan@gmail.com', null, 0)
Select* From KHACHHANG

Create table HOADON(
	MaHD char(12) primary key,
	MaNV char(10) not null,
	MaKH char(10) foreign key references KHACHHANG(MaKH),
	NgayLap Date not null,
)

Create Table CHI_TIET_HOADON(
	MaHD char(12) foreign key references HOADON(MaHD) ON DELETE CASCADE,
	MaSP char(8) foreign key references SANPHAM(MaSP),
	SoLuong int not null check(SoLuong > 0),
)

--(@MaHD char(12), @MaNV char(10), @MaKH char(10),	@NgayLap Date, @SoLuong int)
	--Insert HOADON Values ('000000000000', 'NV00000000', 'KH00000001', '2022-11-27');
	--Insert CHI_TIET_HOADON Values ('000000000000', 'SP000001', 8)
	--Update SANPHAM set SoLuong = SoLuong - 8;
	--Update KHACHHANG set Diem = 8*5;
Go

Create Proc Khoi_Tao_Hoa_Don @MaHD char(12), @MaNV char(10),
	@MaKH char(10), @isSuccess bit out
As
Begin Try
	Insert HOADON Values (@MaHD, @MaNV, @MaKH, CAST(GETDATE() AS Date));
	Set @isSuccess = 1;
End Try
Begin Catch
	Set @isSuccess = 0
	RETURN ERROR_MESSAGE()
End Catch
Go

Create Proc ThemSP_Vao_Hoa_Don @MaHD char(12), @MaKH char(10), @MaSP char(8), @SoLuong int
As
Begin Try
	Declare @DonGia int;
	Select @DonGia = DonGia From SANPHAM Where MaSP = @MaSP
	Insert CHI_TIET_HOADON Values (@MaHD, @MaSP, @SoLuong)
	Update SANPHAM set SoLuong = SoLuong - @SoLuong where MaSP = @MaSP
	Update KHACHHANG set Diem = Diem + CAST(@SoLuong*@DonGia/1000 as int) where MaKH = @MaKH;
END TRY
BEGIN CATCH
    RETURN ERROR_MESSAGE()
END CATCH
Go

Create Proc Xoa_Hoa_Don @MaHD char(12), @isSuccess bit OUT As
Begin Try
	Declare @TongTien float, @MaKH char(10)
	Declare @SP_HD Table(
		MaSP char(8) primary key,
		DonGia float,
		SoLuong int
	)
	Insert Into @SP_HD(MaSP, DonGia, SoLuong) 
		(Select sp.MaSP, sp.DonGia, cthd.SoLuong 
			From CHI_TIET_HOADON cthd join SANPHAM sp on cthd.MaSP = sp.MaSP
			Where cthd.MaHD = @MaHD);
	Select @TongTien = SUM(SoLuong*DonGia)/1000 From @SP_HD
	Select @MaKH = MaKH From HOADON Where MaHD = @MaHD
	Update SANPHAM SET SoLuong = sp.SoLuong + sphd.SoLuong
		From SANPHAM sp JOIN @SP_HD sphd ON sp.MaSP = sphd.MaSP
	
	Select Diem - CAST(@TongTien as int) from KHACHHANG where MaKH = @MaKH
	Update KHACHHANG Set Diem = Diem - CAST(@TongTien as int)
		Where MaKH = @MaKH
	Delete HOADON Where MaHD = @MaHD
	SET @isSuccess = 1;
End Try
Begin Catch
	 SET @isSuccess = 0;
     RETURN ERROR_MESSAGE()
End Catch
Go

Go
Create View HD_SP AS
	Select MaHD, cthd.MaSP, sp.TenSP, sp.DonGia, cthd.SoLuong
	From CHI_TIET_HOADON cthd join
		SANPHAM sp on  cthd.MaSP = sp.MaSP
Go

--------------------------------------------------------------------------------------------------------------
--Get list
	Select * From HOADON

-- Tim Kiem Hoa Don
	Select * From HOADON Where MaHD = '%01%'

-- Tao Hoa Don
	Declare @isSuccess bit;
	Exec Khoi_Tao_Hoa_Don '000000000001', 'NV00000000', 'KH00000001', @isSuccess = @isSuccess OUT;
		Exec ThemSP_Vao_Hoa_Don '000000000001', 'KH00000001', 'SP000001', 8
		Exec ThemSP_Vao_Hoa_Don '000000000001', 'KH00000001', 'SP000002', 12
		Exec ThemSP_Vao_Hoa_Don '000000000001', 'KH00000001', 'SP000010', 1

-- Xoa Hoa Don
	Declare @isSuccess bit;
	Exec Xoa_Hoa_Don '000000000001', @isSuccess = @isSuccess OUT;

-- Hieu thi thong tin hoa don
	Select MaHD, MaSP, TenSP, DonGia, SoLuong From HD_SP Where MaHD = '%01%'

-- Để kiểm tra
Select * from SANPHAM
Select * From KHACHHANG
Select * From HOADON
Select * From CHI_TIET_HOADON


Drop Procedure Khoi_Tao_Hoa_Don
Drop Procedure ThemSP_Vao_Hoa_Don
Drop Procedure Xoa_Hoa_Don
Drop view HD_SP
Drop table CHI_TIET_HOADON
Drop table HOADON
Drop table SANPHAM
Drop table KHACHHANG

sp_help HOADON
sp_help CHI_TIET_HOADON

-- PHAN QUYEN
Use QLCH

Create Table TaiKhoan(
	TaiKhoan varchar(20) primary key,
	MatKhau varchar(20) not null,
	ChucVu nvarchar(10)
)

----------------------------------------------------------------------------------------------
Create table NHANVIEN(
	MaNV char(10) primary key,
	TaiKhoan varchar(20) foreign key references TAIKHOAN(TaiKhoan),
	TenKH nvarchar(30) not null,
	NgaySinh Date,
	DiaChi nvarchar(200),
	ChucVu nvarchar(50) not null,
	Sdt char(10) not null check(Sdt like '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	Email varchar(30) check (Email like '%_@__%.__%' or Email like '%_@__%.__%.__%'),
	HinhAnh image,
	HeSoLuong float not null,
	ngayNghi Date
)

--------------------------------------------------------------------------------------------------
Insert Into TaiKhoan Values ('admin', '123456a@', N'Quản Trị'),
	('nhanvien1', '123456a@', N'Nhân Viên'),
	('nhanvien2', '8554649', N'Nhân Viên'),
	('nhanvien3', 'abcdef', N'Nhân Viên')

Select * From TaiKhoan
Go
Create Procedure LayThongTinDangNhapTrongCSDL(
	@TaiKhoan varchar(20),
	@MatKhau varchar(20),
	@s_tk varchar(18) Out,
	@s_mk varchar(18) Out,
	@s_cv nvarchar(10) Out
	) As
	Select @s_cv = ChucVu From TaiKhoan Where TaiKhoan = @TaiKhoan and MatKhau = @MatKhau
	If @s_cv is not null Begin 
		If @s_cv = N'Nhân Viên'
			Begin	
				 Set @s_tk = 'nhanvien'
				 Set @s_mk = '123456a@'
			End
		Else if @s_cv = N'Quản Trị'
			Begin
				 Set @s_tk = 'sa'
				 Set @s_mk = '123456a@'
			End
	End
Go

Declare @s_tk varchar(18), @s_mk varchar(18), @s_cv nvarchar(10)
Exec LayThongTinDangNhapTrongCSDL 'admin', '123456a@', @s_tk = @s_tk OUT, @s_mk = @s_mk OUT, @s_cv = @s_cv OUT
Select @s_cv

Drop Proc LayThongTinDangNhapTrongCSDL


CREATE LOGIN register
WITH PASSWORD = '123456a@',
DEFAULT_DATABASE = QLCH,
CHECK_POLICY = OFF,
CHECK_EXPIRATION = OFF ;
CREATE USER register FOR LOGIN register

Grant Select On dbo.TaiKhoan To register
Grant Exec On dbo.LayThongTinDangNhapTrongCSDL To register
--Revoke Exec On dbo.LayThongTinDangNhapTrongCSDL To register

execute as user = 'register';
select * From TaiKhoan
Revert

execute as user = 'register';
Declare @s_tk varchar(18), @s_mk varchar(18)
Exec LayThongTinDangNhapTrongCSDL 'nhanvien1', '123456a@', @s_tk = @s_tk OUT, @s_mk = @s_mk OUT
Select @s_tk + @s_mk
Revert

Drop User register
DROP LOGIN register

CREATE LOGIN nhanvien
WITH PASSWORD = '123456a@',
DEFAULT_DATABASE = QLCH,
CHECK_POLICY = OFF,
CHECK_EXPIRATION = OFF;
Create user nhanvien for login nhanvien

GRANT SELECT(MaKH, TaiKhoan, TenKH, NgaySinh), Update(Diem) ON OBJECT::dbo.KHACHHANG TO nhanvien;
Grant Select, Update On dbo.SanPham To nhanvien
Grant All On dbo.HoaDon to nhanvien
Grant All On dbo.Chi_Tiet_HoaDon to nhanvien
Grant Exec On dbo.Khoi_Tao_Hoa_Don to nhanvien
Grant Exec On dbo.ThemSP_Vao_Hoa_Don to nhanvien
Grant Exec On dbo.Xoa_Hoa_Don to nhanvien


-- Tao Hoa Don
	execute as user = 'nhanvien'
	Declare @isSuccess bit;
	Exec Khoi_Tao_Hoa_Don '000000000001', 'NV00000000', 'KH00000001', @isSuccess = @isSuccess OUT;
		Exec ThemSP_Vao_Hoa_Don '000000000001', 'KH00000001', 'SP000001', 8
		Exec ThemSP_Vao_Hoa_Don '000000000001', 'KH00000001', 'SP000002', 12
		Exec ThemSP_Vao_Hoa_Don '000000000001', 'KH00000001', 'SP000010', 1
	Revert
	Select * From Chi_Tiet_HoaDon
	Select Diem From KHACHHANG where MaKH Like '%01'
-- Xoa Hoa Don
	execute as user = 'nhanvien'
	Declare @isSuccess bit;
	Exec Xoa_Hoa_Don '000000000001', @isSuccess = @isSuccess OUT;
	Revert


Drop User nhanvien
DROP LOGIN nhanvien

Select * From TaiKhoan