
Pod::Spec.new do |s|
  s.name         = "RNWootric"
  s.version      = "1.0.0"
  s.summary      = "RNWootric"
  s.description  = <<-DESC
                  RNWootric
                   DESC
  s.homepage     = "https://wootric.com"
  s.license      = "MIT"
  s.author       = { "Wootric" => "support@wootric.com" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/Wootric/RNWootric.git", :tag => s.version.to_s }
  s.source_files  = "ios/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "WootricSDK"

end

  
