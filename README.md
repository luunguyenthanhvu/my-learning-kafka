# My Learning Kafka

<details>
  <summary>
    <b>Theory</b>
  </summary>

<details>
  <summary>
  <b>What is Kafka?</b>
  </summary>

<ul>
    <p>Apache Kafka là một nền tảng mã nguồn mở dùng để <b>xử lý</b> và 
  <b>truyền tải dữ liệu sự kiện theo thời gian thực</b> trên nhiều máy chủ
  (open-source distributed <b>event streaming</b> platform).</p>

* span

> Mỗi transaction giao dịch sẽ tạo ra một <b> event </b> rồi gửi đến Kafka server.
>
> + Trên ứng dụng thực tế, các transaction giao dịch có thể diễn ra đồng thời với 1 ố lượng rất
>   lớn trong vài giây, việc này tạo ra một loạt các event gửi đến Kafka, quá trình này gọi
>   là <b> Create / Generate </b> realtime event stream of data. Khi Kafka server nhận được data,
>   nó cần phải xử lí.
>   >

+ Giả sử ứng dụng có một <i> client application </i> có vai trò là đọc data từ Kafka và xử lí
  yêu cầu đặt vé máy bay và client application muốn hạn chế mỗi user chỉ được phép thực hiện 10
  transaction mỗi ngày. Nếu vượt mức transaction, application sẽ <b> send một mail thông
  báo </b> đến email của user.

> + Trong trường hợp như trên, client application phải liên tục thực hiện <b> validation </b>
>   kiểm tra số lượng transaction của mỗi user. Nghĩa là application phải <b> liên tục </b>
>   lắng
>   nghe Kafka Server để nhận message. Quá trình này được gọi là <b> Processing realtime event
>   stream of data </b>.

<li>
Khi nói về <b> distributed </b>, thì trong Microservices, định nghĩa distributed nghĩa là phân tán nhiều máy chủ đến nhiều node khác nhau hay nhiều region khác nhau để cân bằng tải và tránh down time:
  <ul>
  <li>
Kafka là distributed platform, nghĩa là ta cũng có thể phân tán Kafka server chạy ở nhiều region khác nhau.
  </li>
<li>
Trong trường hợp có một server nào đó bị sập, server khác sẽ thay thế nhận lấy traffic để tránh trường hợp cả hệ thống bị sập.
</li>
</ul>
</li>


</ul>

* <details>
    <summary>
    <b>Why do we need Kafka?</b>
    </summary>

  <b>Ví dụ 1: </b>

  <ul>
    <li>Services A có 1 message quan trọng cần phải nhận vì message này liên quan đến logic của toàn bộ hệ thống hay
  liên quan đến 1 giao dịch. Thế nhưng trong lúc gửi thì server A đang bị sập và không thể nhận được => mất mát  liệu
  trong hệ thống (tệ nhất là sai logic của toàn bộ hệ thống). </li>
  <li>
  Trong trường hợp này, Kafka đóng vai trò như là một hòm đưa thư, khi message đến service, message sẽ được 
  đặt vào hòm thư này và khi hệ thống hoạt động trở lại, chỉ việc lấy tất cả message từ hòm thư và xử lí đồng loạt.</li>
  </ul>

  <b>Ví dụ 2: </b>

  <p>Giả sử chúng ta có 4 service cần giao tiếp và kết nối với 5 service/server khác nhau</p>

</details>
</details>
</details>
