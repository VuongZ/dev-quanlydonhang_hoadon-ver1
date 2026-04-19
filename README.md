Hướng Dẫn Sử Dụng
1. Quản lý khách hàng

Vào menu Khách hàng → nhấn + Thêm khách hàng
Điền thông tin: Tên, Mã số thuế, Điện thoại, Địa chỉ
Tìm kiếm theo tên hoặc mã số thuế

2. Quản lý mẫu hóa đơn

Vào menu Mẫu hóa đơn → nhấn + Thêm mẫu
Soạn nội dung HTML với các placeholder:

PlaceholderÝ nghĩa{{invoiceNumber}}Số hóa đơn{{invoiceDate}}Ngày hóa đơn{{customerName}}Tên khách hàng{{customerAddress}}Địa chỉ khách hàng{{customerTaxCode}}Mã số thuế{{totalAmount}}Tổng tiền trước thuế{{taxRate}}Thuế suất{{finalAmount}}Thành tiền sau thuế{{details}}Bảng chi tiết hàng hóa
3. Tạo hóa đơn

Vào menu Hóa đơn → nhấn + Tạo hóa đơn
Chọn khách hàng, mẫu hóa đơn
Thêm dòng hàng hóa (tên, số lượng, đơn giá)
Hệ thống tự tính tổng tiền, thuế, thành tiền

4. Xuất & In hóa đơn

Trong danh sách hóa đơn → nhấn In / Xuất PDF

5. Quản lý phiếu thu chi

Vào menu Phiếu thu chi
Tạo phiếu Thu (tiền vào) hoặc Chi (tiền ra)
Liên kết với khách hàng tương ứng

❗ Lưu Ý Quan Trọng
Không thể xóa mẫu hóa đơn nếu đang có hóa đơn sử dụng mẫu đó
Trường content_html phải là kiểu LONGTEXT trong MySQL
Phải chạy backend trước, sau đó mới chạy frontend
Backend và frontend phải chạy cùng lúc

Ngôn ngữ sử dụng
Backend:String Boot thường chạy ở port: http://localhost:8080/
Frontend:react thường chạy bằng lệnh npm start và ở port: http://localhost:3000/

🐛 Xử Lý Lỗi Thường Gặp
LỗiNguyên nhânCách xử lýCannot connect to databaseSai password hoặc MySQL chưa chạyKiểm tra MySQL và application.properties500 Internal Server ErrorLỗi backendXem log terminal IntelliJCORS errorFrontend gọi sai portĐảm bảo backend chạy port 8080npm install failedCache lỗiChạy npm cache clean --force rồi npm install lạiBlank page trên frontendLỗi JavaScriptMở DevTools (F12) xem Console
