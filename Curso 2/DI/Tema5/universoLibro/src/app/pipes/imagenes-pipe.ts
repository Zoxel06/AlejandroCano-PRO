import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imagenes',
  standalone: false,
})
export class ImagenesPipe implements PipeTransform {

  transform(paginas: number, ...args: unknown[]): string {

    if(paginas < 150){
      return "https://cdn-icons-png.flaticon.com/512/1945/1945940.png"
    } else if(paginas < 250) {
      return "https://cdn-icons-png.flaticon.com/512/8832/8832880.png"
    } else if(paginas < 400){
      return "https://cdn-icons-png.flaticon.com/512/3145/3145765.png"
    } else {
      return "https://cdn-icons-png.flaticon.com/512/2232/2232688.png"
    }

    return "https://img.freepik.com/vector-premium/ilustracion-vectorial-sello-sello-goma-grunge_140916-29789.jpg?semt=ais_user_personalization&w=740&q=80";
  }

}
